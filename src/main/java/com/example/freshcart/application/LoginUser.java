package com.example.freshcart.application;

import com.example.freshcart.domain.Role;

/**
 저장소에서 조회한 유저의 email/password 정보.
 스프링 시큐리티의 UserDetails 와 유사.
 */

public class LoginUser {

  private String email;
  private Role role;

  public LoginUser(String email, Role role) {
    this.email = email;
    this.role = role;
  }

  public static LoginUser of(String email, Role role) {
    return new LoginUser(email, role);
  }

  public String getEmail() {
    return email;
  }

  public Role getRole() {
    return role;
  }
}
