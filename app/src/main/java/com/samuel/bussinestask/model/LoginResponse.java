package com.samuel.bussinestask.model;

public class LoginResponse {

    private String token;
    private String role;
    private Integer userId;


    public Integer getUserId() {
        return userId;
    }
    public String getToken() {
        return token;
    }

    public String getRole() {
        return role;
    }
}