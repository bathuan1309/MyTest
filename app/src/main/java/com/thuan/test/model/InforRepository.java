package com.thuan.test.model;

import android.app.Application;

import androidx.lifecycle.MutableLiveData;

import com.thuan.test.service.RestApiService;
import com.thuan.test.service.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Future;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class InforRepository {
    private List<Infor> infors;
    private MutableLiveData<List<Infor>> mutableLiveData = new MutableLiveData<>();
    private Application application;

    public InforRepository(Application application) {
        this.application = application;
    }

    public MutableLiveData<List<Infor>> getMutableLiveData() {
        RestApiService apiService = RetrofitInstance.getApi();

        Call<List<Infor>> call = apiService.getJsons();

        call.enqueue(new Callback<List<Infor>>() {
            @Override
            public void onResponse(Call<List<Infor>> call, Response<List<Infor>> response) {
                infors = new ArrayList<>(response.body());
                mutableLiveData.setValue(infors);
            }

            @Override
            public void onFailure(Call<List<Infor>> call, Throwable t) {

            }
        });
        return mutableLiveData;
    }

}
