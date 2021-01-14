package com.thuan.test.service;

import com.thuan.test.model.Infor;

import java.util.List;

import retrofit2.Call;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

public interface RestApiService {
    @GET("posts")
    Call<List<Infor>> getJsons();
}
