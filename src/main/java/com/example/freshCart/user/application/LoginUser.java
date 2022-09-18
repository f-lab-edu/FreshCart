package com.example.freshcart.user.application;

/**
 저장소에서 조회한 유저의 email/password 정보.
 */

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
