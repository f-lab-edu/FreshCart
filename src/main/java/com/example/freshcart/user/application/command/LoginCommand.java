package com.example.freshcart.user.application.command;

public class LoginCommand {

  private String email;

  private String password;

  public LoginCommand(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public LoginCommand() {
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

}
