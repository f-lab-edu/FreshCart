package com.example.freshcart.application.command;

import com.example.freshcart.domain.Role;
import javax.validation.constraints.NotNull;

/**
 사용자가 필수 정보를 담아서 회원 가입 요청.
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
