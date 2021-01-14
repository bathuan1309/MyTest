package com.thuan.test.service;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitInstance {
    private static RestApiService api;
    private static final String BASE_URL = "https://jsonplaceholder.typicode.com/";

    public static RestApiService getApi() {
        if (api == null) {
            Retrofit retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();

            api = retrofit.create(RestApiService.class);
        }
        return api;
    }
}
