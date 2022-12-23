package com.example.freshcart.user.application.command;

import com.example.freshcart.authentication.Role;
import lombok.Builder;
import lombok.Getter;

@Getter
public class SignupCommand {

  private String email;

  private String password;

  private String phoneNumber;

  private String name;

  private Role role;

  @Builder
  public SignupCommand(String email, String password, String phoneNumber, String name,
      Role role) {
    this.email = email;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.name = name;
    this.role = role;
  }

}
