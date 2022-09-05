package com.example.freshCart.application;

public class LoginUser {

  private String email;
  private String password;

  public LoginUser(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public static LoginUser of(String email, String password) {
    return new LoginUser(email, password);
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }
}
