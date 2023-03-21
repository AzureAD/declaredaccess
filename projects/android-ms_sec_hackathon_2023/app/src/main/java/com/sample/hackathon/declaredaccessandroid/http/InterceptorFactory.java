package com.sample.hackathon.declaredaccessandroid.http;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.microsoft.identity.client.ISingleAccountPublicClientApplication;

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

            final Request outboundRequest = chain.request();
            // TODO define MSAL-based interceptor here

            return chain.proceed(outboundRequest);
        };
    }

    @NonNull
    static Interceptor createNetworkInterceptor() {
        return chain -> {
            // NO-OP for now. Don't think we will need this.
            return chain.proceed(chain.request());
        };
    }
}
