package com.example.postapiexample.ApiUtills;

public class Constant {

    public static String BASE_URL = "http://13.127.111.2:8090/api/";

    public static ApiServices getUserService(){
        return RetrofitClient.getClient(BASE_URL).create(ApiServices.class);
    }
}
