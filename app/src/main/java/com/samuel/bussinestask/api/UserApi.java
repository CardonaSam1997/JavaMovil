package com.samuel.bussinestask.api;

import com.samuel.bussinestask.model.UserCreateRequest;
import com.samuel.bussinestask.model.UserResponse;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface UserApi {
    @POST("users")
    Call<UserResponse> register(@Body UserCreateRequest dto);
}
