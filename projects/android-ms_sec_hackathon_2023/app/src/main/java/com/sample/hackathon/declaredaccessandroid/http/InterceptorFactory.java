package com.sample.hackathon.declaredaccessandroid.http;

import androidx.annotation.NonNull;

import okhttp3.Interceptor;

class InterceptorFactory {

    @NonNull
    static Interceptor createApplicationInterceptor() {
        return chain -> {
            // TODO define MSAL-based interceptor here
            return null;
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
