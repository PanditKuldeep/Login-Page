package com.example.webwerks.loginmodule.service;

import com.example.webwerks.loginmodule.model.response.LoginResponse;


import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.POST;

public interface ApiServices {


   @POST("users/login")
   @FormUrlEncoded
   Single<LoginResponse> userLogin(
           @Field("email") String email,
           @Field("password") String password);
}
