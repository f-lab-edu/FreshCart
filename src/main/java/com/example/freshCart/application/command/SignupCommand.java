package com.example.freshCart.application.command;

import com.example.freshCart.domain.Role;
import javax.validation.constraints.NotNull;

/*
가입을 요청하는 객체.
 */
public class SignupCommand {

  @NotNull private String email;

  @NotNull private String password;

  @NotNull private String name;

  @NotNull private Role role;

  public SignupCommand(String email, String password, String name, Role role) {
    this.email = email;
    this.password = password;
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
}
