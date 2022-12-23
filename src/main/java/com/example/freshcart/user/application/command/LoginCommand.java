package com.example.freshcart.user.application.command;

import lombok.Builder;
import lombok.Getter;

@Getter
public class LoginCommand {

  private String email;

  private String password;

  @Builder
  public LoginCommand(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public LoginCommand() {
  }

}
