package com.sample.hackathon.declaredaccessandroid.http;

import static com.sample.hackathon.declaredaccessandroid.msal.conf.ResourceConfigMetadata.RESOURCE_CONFIGURATION_MAP;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.microsoft.identity.client.AcquireTokenSilentParameters;
import com.microsoft.identity.client.IAccount;
import com.microsoft.identity.client.IAuthenticationResult;
import com.microsoft.identity.client.ICurrentAccountResult;
import com.microsoft.identity.client.ISingleAccountPublicClientApplication;
import com.microsoft.identity.client.exception.MsalException;
import com.sample.hackathon.declaredaccessandroid.msal.conf.ResourceConfiguration;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Request;

class InterceptorFactory {

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
                    final String accessToken = acquireToken(publicClientApplication, configuration);

                    // Attach it to the request as a bearer token
                    outboundRequest = outboundRequest
                            .newBuilder()
                            .addHeader("Authorization", "Bearer " + accessToken)
                            .build();
                } catch (final MsalException e) {
                    // TODO Abort & Handle...
                    Log.e("Interceptor Factory","");
                }
            }

            return chain.proceed(outboundRequest);
        };
    }

    private static String acquireToken(
            @NonNull final ISingleAccountPublicClientApplication publicClientApplication,
            @NonNull final ResourceConfiguration configuration) throws MsalException {
        // Get our user...
        try {
            final ICurrentAccountResult currentAccountResult = publicClientApplication.getCurrentAccount();
            final IAccount account = currentAccountResult.getCurrentAccount();

            // Construct our params...
            final List<String> scopes = new ArrayList<>();
            scopes.add(configuration.getDefaultScope());

            final AcquireTokenSilentParameters parameters =
                    new AcquireTokenSilentParameters.Builder()
                            .forAccount(account)
                            .withScopes(scopes)
                            .fromAuthority(account.getAuthority())
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
