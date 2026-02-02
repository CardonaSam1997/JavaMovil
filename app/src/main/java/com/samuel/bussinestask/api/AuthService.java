package com.samuel.bussinestask.api;

import com.samuel.bussinestask.model.ForgotPasswordRequest;
import com.samuel.bussinestask.model.LoginRequest;
import com.samuel.bussinestask.model.LoginResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Headers;
import retrofit2.http.POST;

public interface AuthService {

    @POST("login")
    Call<LoginResponse> login(@Body LoginRequest request);

    @Headers("Content-Type: application/json")
    @POST("login/forgot-password")
    Call<String> forgotPassword(@Body ForgotPasswordRequest request);

}