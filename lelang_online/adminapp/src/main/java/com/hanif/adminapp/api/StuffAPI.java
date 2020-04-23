package com.alfi.adminapp.api;

import com.alfi.adminapp.model.Result;

import retrofit2.Call;
import retrofit2.Response;
import retrofit2.http.GET;

public interface StuffAPI {

    @GET("barang")
    Call<Result> view();
}
