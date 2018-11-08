package com.saliim.latihansearch.network;

import com.saliim.latihansearch.pojo.ResponseSearch;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface ApiService {

    @GET("search/movie")
    Call<ResponseSearch> searchin(@Query("api_key")String apiKey,
                                  @Query("language")String language,
                                  @Query("query")String query);
}
