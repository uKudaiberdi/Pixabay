package com.example.pixabay.network.model;


import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface PixabayApi {

    @GET("api/")
    Call<PixabayModel> getImages(@Query("key") String key,
                                 @Query("q") String query,
                                 @Query("page") int page,
                                 @Query("per_page") int perPage);
}

