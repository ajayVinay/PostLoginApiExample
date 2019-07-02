package com.example.postapiexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.postapiexample.ApiUtills.ApiServices;
import com.example.postapiexample.models.LoginResObj;
import com.example.postapiexample.models.TokenRequest;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    EditText username;
    EditText password;
    Button login;
    public static String BASE_URL = "";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        username = findViewById(R.id.edtUsername);
        password = findViewById(R.id.edtPassword);

        login = findViewById(R.id.btnLogin);

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        final ApiServices services = retrofit.create(ApiServices.class);

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = username.getText().toString().trim();
                String pass = password.getText().toString().trim();

                Boolean cancel = false;
                View focusView = null;
                if (!TextUtils.isEmpty(pass) && !isPasswordValid(pass)) {

                    password.setError("This password is to short");
                    focusView = password;
                    cancel = true;
                }
                if (TextUtils.isEmpty(name)) {
                    username.setError("This Field is required");
                    focusView = username;
                    cancel = true;
                } else if (!isEmailValid(name)) {

                    username.setError("this email address is invalid");
                    focusView = username;
                    cancel = true;
                }
                if (cancel) {
                    focusView.requestFocus();
                } else {
                    //Toast.makeText(getApplicationContext(),"Attempting Login ",Toast.LENGTH_LONG).show();

                    TokenRequest tokenRequest = new TokenRequest();
                    tokenRequest.setUsername(username.getText().toString().trim());
                    tokenRequest.setPassword(password.getText().toString().trim());
                    tokenRequest.setGrant_type("password");   // this is parameter pass in url
                    tokenRequest.setClient_id("f3d259ddd3ed8ff3843839b");
                    tokenRequest.setClient_secret("4c7f6f8fa93d59c4550ae8c4a95b");
                    final Call<LoginResObj> resObjCall = services.getUserToken(tokenRequest);
                    resObjCall.enqueue(new Callback<LoginResObj>() {
                        @Override
                        public void onResponse(Call<LoginResObj> call, Response<LoginResObj> response) {

                            int statuscode = response.code();
                            LoginResObj loginResObj = response.body();
                          //  showProgress(false);
                            Log.d("MainActivity", "onResponse: "+statuscode);
                        }

                        @Override
                        public void onFailure(Call<LoginResObj> call, Throwable t) {

                            Log.d("MainActivity", "onFailure: "+t.getMessage());

                           // showProgress(false);

                        }
                    });

                }

            }
            private boolean isEmailValid(String email) {
                return email.contains("@");
            }

            private boolean isPasswordValid(String pass) {
                return pass.length() > 4;

            }
        });


    }
}
