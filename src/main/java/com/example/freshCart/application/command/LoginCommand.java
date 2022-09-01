package com.example.freshCart.application.command;

public class LoginCommand {
    private String email;
    private String password;

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

  public LoginCommand(String email, String password) {
        this.email = email;
        this.password = password;
    }
}
