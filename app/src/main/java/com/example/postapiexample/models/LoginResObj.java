package com.example.postapiexample.models;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class LoginResObj {

    @SerializedName("access_token")
    @Expose
    String access_token;
    @SerializedName("token_type")
    @Expose
    String token_type;
    @SerializedName("expire_in")
    @Expose
    String expire_in;
    @SerializedName("refresh_token")
    @Expose
    String refresh_token;



    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public String getToken_type() {
        return token_type;
    }

    public void setToken_type(String token_type) {
        this.token_type = token_type;
    }

    public String getExpire_in() {
        return expire_in;
    }

    public void setExpire_in(String expire_in) {
        this.expire_in = expire_in;
    }

    public String getRefresh_token() {
        return refresh_token;
    }

    public void setRefresh_token(String refresh_token) {
        this.refresh_token = refresh_token;
    }
}

