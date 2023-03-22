package com.sample.hackathon.declaredaccessandroid.graph;

import com.sample.hackathon.declaredaccessandroid.graph.dto.User;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

public interface GraphService {

    /**
     * Calls the /me endpoint.
     * TODO - Use the HttpClient interceptor to attach the bearer token.
     *
     * @return The serialized User object.
     */
    @Headers({
            "Accept: application/json"
    })
    @GET("me")
    Call<User> getMe();
}
