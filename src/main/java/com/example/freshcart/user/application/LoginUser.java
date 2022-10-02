package com.example.freshcart.user.application;

import com.example.freshcart.global.domain.Role;

/**
 * 저장소에서 조회한 유저의 email/password 정보. 스프링 시큐리티의 UserDetails 와 유사.
 */

public class LoginUser {

  private Long id;
  private String email;
  private Role role;

  public LoginUser(Long id, String email, Role role) {
    this.id = id;
    this.email = email;
    this.role = role;
  }

  public static LoginUser of(Long id, String email, Role role) {
    return new LoginUser(id, email, role);
  }

  public Long getId() {
    return id;
  }

  public String getEmail() {
    return email;
  }

  public Role getRole() {
    return role;
  }


}
