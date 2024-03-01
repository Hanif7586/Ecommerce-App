package com.hrtsoft.retrofit;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiInterface {

    @GET("products")
    Call<Model>getResponse();

}
