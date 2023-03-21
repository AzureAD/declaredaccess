package com.sample.hackathon.declaredaccessandroid.http;

import androidx.annotation.NonNull;

import okhttp3.Interceptor;

class InterceptorFactory {

    @NonNull
    static Interceptor createApplicationInterceptor() {
        return chain -> {
            // TODO define interceptor here
            return null;
        };
    }

    @NonNull
    static Interceptor createNetworkInterceptor() {
        return chain -> {
            // TODO define interceptor here
            return null;
        };
    }
}
