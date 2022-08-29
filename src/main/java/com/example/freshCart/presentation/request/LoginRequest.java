package com.example.freshCart.presentation.request;

public class LoginRequest {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public LoginRequest(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
