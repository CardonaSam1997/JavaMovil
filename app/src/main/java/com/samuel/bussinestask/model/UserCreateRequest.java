package com.samuel.bussinestask.model;

public class UserCreateRequest{
    private String userName;
    private String email;
    private String password;
    private String role;

    public UserCreateRequest(String userName, String email, String password, String role) {
        this.userName = userName;
        this.email = email;
        this.password = password;
        this.role = role;
    }
}
