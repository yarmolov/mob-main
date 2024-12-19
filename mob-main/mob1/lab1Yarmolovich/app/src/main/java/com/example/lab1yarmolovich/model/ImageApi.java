package com.example.lab1yarmolovich.model;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ImageApi {
    @GET("search/photos")
    Call<com.example.lab1yarmolovich.model.ImageResponse> searchImages(
            @Query("query") String query,
            @Query("client_id") String apiKey,
            @Query("page") int page,
            @Query("per_page") int perPage
    );
}