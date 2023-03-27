package com.sample.hackathon.declaredaccessandroid.http;

import static com.sample.hackathon.declaredaccessandroid.http.InterceptorFactory.createApplicationInterceptor;
import static com.sample.hackathon.declaredaccessandroid.http.InterceptorFactory.createNetworkInterceptor;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.navigation.NavHostController;

import com.microsoft.identity.client.ISingleAccountPublicClientApplication;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

/**
 * Simple Factory class for creating HTTP clients.
 */
public class HttpClientFactory {

    @NonNull
    public static OkHttpClient createHttpClient(
            @NonNull final Interceptor applicationInterceptor,
            @NonNull final Interceptor networkInterceptor) {
        return new OkHttpClient()
                .newBuilder()
                .addInterceptor(applicationInterceptor)
                .addNetworkInterceptor(networkInterceptor)
                .build();
    }

    @NonNull
    public static OkHttpClient createDefaultHttpClient(
            @Nullable final ISingleAccountPublicClientApplication publicClientApplication) {
        return createHttpClient(
                createApplicationInterceptor(publicClientApplication),
                createNetworkInterceptor()
        );
    }

}
