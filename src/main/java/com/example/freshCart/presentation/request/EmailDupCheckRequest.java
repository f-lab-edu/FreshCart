package com.example.freshCart.presentation.request;

public class EmailDupCheckRequest {
    private String email;

    public EmailDupCheckRequest(String email) {
        this.email = email;
    }

    public String getEmail() {
        return email;
    }
}
