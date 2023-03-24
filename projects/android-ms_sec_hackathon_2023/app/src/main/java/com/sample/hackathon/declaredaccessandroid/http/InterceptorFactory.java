package com.sample.hackathon.declaredaccessandroid.http;

import static com.sample.hackathon.declaredaccessandroid.msal.conf.ResourceConfigMetadata.RESOURCE_CONFIGURATION_MAP;
import static com.sample.hackathon.declaredaccessandroid.navigation.AuthNavHostKt.navigateToInteractionRequired;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavHostController;

import com.microsoft.identity.client.AcquireTokenSilentParameters;
import com.microsoft.identity.client.IAccount;
import com.microsoft.identity.client.IAuthenticationResult;
import com.microsoft.identity.client.ICurrentAccountResult;
import com.microsoft.identity.client.ISingleAccountPublicClientApplication;
import com.microsoft.identity.client.exception.MsalException;
import com.microsoft.identity.client.exception.MsalUiRequiredException;
import com.sample.hackathon.declaredaccessandroid.msal.conf.ResourceConfiguration;
import com.sample.hackathon.declaredaccessandroid.msal.exception.NoSignedInUserException;
import com.sample.hackathon.declaredaccessandroid.msal.exception.UiRequiredRequiredException;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import okhttp3.Interceptor;
import okhttp3.Request;

public class InterceptorFactory {

    public static final String TAG = "Interceptor Factory";

    private static NavHostController navHostController = null;

    public static void setNavHostController(
            @NonNull NavHostController navHostController) {
        InterceptorFactory.navHostController = navHostController;
    }

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
                    Log.e(TAG, "We encountered an error: " + Log.getStackTraceString(e));
                    if (e instanceof MsalUiRequiredException) {
                        // We cannot resolve this issue locally, pass it up to the caller for
                        // user remediation
                        Log.e("InterceptorFactory", "UiRequiredRequiredException");
                        throw new UiRequiredRequiredException(e);
                    }
                }
            }

            return chain.proceed(outboundRequest);
        };
    }

    private static String acquireToken(
            @NonNull final ISingleAccountPublicClientApplication publicClientApplication,
            @NonNull final ResourceConfiguration configuration) throws MsalException, NoSignedInUserException {
        // Get our user...
        try {
            final ICurrentAccountResult currentAccountResult = publicClientApplication.getCurrentAccount();
            final IAccount account = currentAccountResult.getCurrentAccount();

            if (null == account) {
                // There is no current / signed-in account
                Log.e("InterceptorFactory", "No current account found.");
                if (navHostController != null) {
                    navigateToInteractionRequired(InterceptorFactory.navHostController);
                } else {
                    Log.d("InterceptorFactory", "NavHostController is not set");
                }
                // TODO we're navigating to a different screen, and abandoning the request. We
                // should handle that more gracefully than throwing an exception.
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
