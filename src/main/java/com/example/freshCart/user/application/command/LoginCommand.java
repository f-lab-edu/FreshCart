package com.example.freshcart.user.application.command;

/**
 사용자가 로그인 시도 시 정보를 담아서 요청.
 */
public class LoginCommand {
  private String email;
  private String password;

  public LoginCommand(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }
}