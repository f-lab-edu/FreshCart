package com.example.freshcart.user.application.command;

import com.example.freshcart.authentication.Role;

public class SignupCommand {

  private String email;

  private String password;

  private String phoneNumber;

  private String name;

  private Role role;

  public SignupCommand(String email, String password, String phoneNumber, String name,
      Role role) {
    this.email = email;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.name = name;
    this.role = role;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getName() {
    return name;
  }

  public Role getRole() {
    return role;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

}
