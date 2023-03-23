package com.sample.hackathon.declaredaccessandroid.http;

import static com.sample.hackathon.declaredaccessandroid.msal.conf.ResourceConfigMetadata.RESOURCE_CONFIGURATION_MAP;

import android.util.Base64;
import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.microsoft.identity.client.AcquireTokenSilentParameters;
import com.microsoft.identity.client.IAccount;
import com.microsoft.identity.client.IAuthenticationResult;
import com.microsoft.identity.client.ICurrentAccountResult;
import com.microsoft.identity.client.ISingleAccountPublicClientApplication;
import com.microsoft.identity.client.claims.ClaimsRequest;
import com.microsoft.identity.client.exception.MsalException;
import com.microsoft.identity.client.exception.MsalUiRequiredException;
import com.sample.hackathon.declaredaccessandroid.msal.conf.ResourceConfiguration;
import com.sample.hackathon.declaredaccessandroid.msal.exception.NoSignedInUserException;
import com.sample.hackathon.declaredaccessandroid.msal.exception.UiRequiredRequiredException;

import java.io.IOException;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

class InterceptorFactory {

    public static final String TAG = "Interceptor Factory";

    private static final String AUTHENTICATE_HEADER = "WWW-authenticate";

    @NonNull
    static Interceptor createApplicationInterceptor(
            @Nullable final ISingleAccountPublicClientApplication publicClientApplication) {
        return chain -> {
            if (null == publicClientApplication) {
                // MSAL instance not supplied, nothing to do. Handle the requests with no changes.
                return chain.proceed(chain.request());
            }

            // Grab our outbound request
            Request outboundRequest = chain.request();

            // Grab its destination URL
            final URL requestUrl = outboundRequest.url().url(); // ugly!

            // See if we have a config for it...
            final ResourceConfiguration configuration =
                    RESOURCE_CONFIGURATION_MAP.get(requestUrl.getHost());

            if (null != configuration) { // We have a config for this endpoint
                try {
                    // Get a token from MSAL
                    final String accessToken = acquireToken(
                            publicClientApplication,
                            configuration,
                            null
                    );

                    // Attach it to the request as a bearer token
                    outboundRequest = outboundRequest
                            .newBuilder()
                            .addHeader("Authorization", "Bearer " + accessToken)
                            .build();
                } catch (final MsalException e) {
                    Log.e(TAG, "We encountered an error: " + Log.getStackTraceString(e));
                    if (e instanceof MsalUiRequiredException) {
                        // We cannot resolve this issue locally, pass it up to the caller for
                        // user remediation
                        throw new UiRequiredRequiredException(e);
                    } else {
                        // TBH I don't know what other exception might occur here off hand.
                        // There could be ServiceException is ESTS is down... not going to try to account for that!
                        // There could be a ClientException if the request is malformed, which shouldn't happen either.
                        // So let's just rethrow and see if this causes issues for something I'm not thinking about...
                        throw new RuntimeException(e);
                    }
                }

                // OK - we got this far. We got a token from MSAL and put it on our request.
                // Let's try the request and see if works. If we get a 200 OK, then all good.
                // If we get a 401 and the Authorization header contains a challenge, parse it and try again.
                final Response firstAttempt = chain.proceed(outboundRequest);

                if (firstAttempt.isSuccessful()) {
                    // Hooray, return the result
                    return firstAttempt;
                } else if (401 == firstAttempt.code() && hasClaimsChallenge(firstAttempt)) {
                    final String jsonClaimsChallenge = extractClaimsChallenge(firstAttempt);
                    try {
                        return retryRequest(
                                publicClientApplication,
                                firstAttempt.request(),
                                jsonClaimsChallenge,
                                configuration,
                                chain
                        );
                    } catch (MsalException e) {
                        throw new RuntimeException(e);
                    }
                } else {
                    return firstAttempt;
                }

            }

            return chain.proceed(outboundRequest);
        };
    }

    private static Response retryRequest(
            @NonNull final ISingleAccountPublicClientApplication publicClientApplication,
            @NonNull final Request request,
            @NonNull final String jsonClaimsChallenge,
            @NonNull final ResourceConfiguration configuration,
            @NonNull final Interceptor.Chain chain) throws MsalException, IOException {
        final ClaimsRequest claimsRequest =
                ClaimsRequest.getClaimsRequestFromJsonString(jsonClaimsChallenge);

        final String accessToken = acquireToken(
                publicClientApplication,
                configuration,
                claimsRequest
        );

        final Request retryRequest =
                request.newBuilder()
                        .addHeader("Authorization", "Bearer " + accessToken)
                        .build();

        return chain.proceed(retryRequest);
    }

    private static String extractClaimsChallenge(@NonNull final Response firstAttempt) {
        final String authenticateHeader = firstAttempt.header(AUTHENTICATE_HEADER);
        final String extractEncodedClaimsBlob = extractClaimsBlob(authenticateHeader);
        return base64Decode(extractEncodedClaimsBlob);
    }

    private static String extractClaimsBlob(@NonNull final String authenticateHeader) {
        final String prefixKey = "claims=";

        String trimmedHeader = authenticateHeader.replaceFirst(",", "");
        String[] headerParts = trimmedHeader.split(" ");
        for (final String part : headerParts) {
            if (part.startsWith(prefixKey)) {
                return part.replaceFirst(prefixKey, "");
            }
        }

        // Do not actually do this in the real world - demo purposes only!
        throw new RuntimeException("Failed to parse claims challenge");
    }

    private static String base64Decode(@NonNull final String extractEncodedClaimsBlob) {
        try {
            byte[] decodedPayload = Base64.decode(extractEncodedClaimsBlob, Base64.URL_SAFE);
            return new String(decodedPayload, StandardCharsets.UTF_8);
        } catch (Exception e) {
            // Something went wrong...
            Log.e(TAG, "Failed to decode challenge blob. " + Log.getStackTraceString(e));
        }

        return null;
    }

    private static boolean hasClaimsChallenge(@NonNull final Response firstAttempt) {
        final String authHeader = firstAttempt.header(AUTHENTICATE_HEADER);
        return null != authHeader && authHeader.contains("claims");
    }

    private static String acquireToken(
            @NonNull final ISingleAccountPublicClientApplication publicClientApplication,
            @NonNull final ResourceConfiguration configuration,
            @Nullable final ClaimsRequest claimsRequest) throws MsalException, NoSignedInUserException {
        // Get our user...
        try {
            final ICurrentAccountResult currentAccountResult = publicClientApplication.getCurrentAccount();
            final IAccount account = currentAccountResult.getCurrentAccount();

            if (null == account) {
                // There is no current / signed-in account
                throw new NoSignedInUserException("No current account found.");
            }

            // Construct our params...
            final List<String> scopes = new ArrayList<>();
            scopes.add(configuration.getDefaultScope());

            final AcquireTokenSilentParameters parameters =
                    new AcquireTokenSilentParameters.Builder()
                            .forAccount(account)
                            .withScopes(scopes)
                            .fromAuthority(account.getAuthority())
                            .withClaims(claimsRequest)
                            .build();

            // Make the request to MSAL...
            final IAuthenticationResult result =
                    publicClientApplication.acquireTokenSilent(parameters);

            // Extract & return the token...
            return result.getAccessToken();
        } catch (final InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    @NonNull
    static Interceptor createNetworkInterceptor() {
        return chain -> {
            // NO-OP for now. Don't think we will need this.
            return chain.proceed(chain.request());
        };
    }
}
