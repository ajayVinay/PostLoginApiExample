package com.example.postapiexample.ApiUtills;

import com.example.postapiexample.models.LoginResObj;
import com.example.postapiexample.models.TokenRequest;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;


public interface ApiServices {

    @POST("auth")
    Call<LoginResObj> getUserToken(@Body TokenRequest tokenRequest);
}
