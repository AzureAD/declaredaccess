package com.sample.hackathon.declaredaccessandroid.graph;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.microsoft.identity.client.ISingleAccountPublicClientApplication;
import com.sample.hackathon.declaredaccessandroid.http.HttpClientFactory;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Factory class for creating instances of {@link GraphService}.
 */
public class GraphServiceFactory {

    private static final String BASE_URL_GRAPH = "https://graph.microsoft.com/v1.0/";

    // TODO make blocking and use Future
    public static void init(
            @Nullable final ISingleAccountPublicClientApplication publicClientApplication) {
        if (instance == null) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    instance = createDefaultGraphService(publicClientApplication);
                }
            }).start();
        }
    }
    private static GraphService instance;

    public static GraphService getInstance() {
        if (instance == null) {
            throw new IllegalArgumentException("GraphService must be initialised first");
        }
        return instance;
    }

    /**
     * Creates a new {@link GraphService} instance.
     *
     * @param httpClient The underlying HTTP client to use.
     * @return An instance of the service object.
     */
    private static GraphService createGraphService(@NonNull final OkHttpClient httpClient) {
        final Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL_GRAPH)
                .client(httpClient)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        return retrofit.create(GraphService.class);
    }

    /**
     * Creates a default instance of the GraphService.
     *
     * @return An instance of the GraphService initialized with defaults for the Microsoft
     * Security Hackathon.
     */
    private static GraphService createDefaultGraphService(
            @Nullable final ISingleAccountPublicClientApplication publicClientApplication) {
        return createGraphService(HttpClientFactory.createDefaultHttpClient(publicClientApplication));
    }

}
