package com.example.freshcart.user.domain;

import com.example.freshcart.authentication.Role;
import lombok.Builder;
import lombok.Getter;

/**
 * NotNull + Valid(@RequestBody와 함께)로 필드가 입력되었는지 점검 .
 */

@Getter
public class User {

  private Long id;

  private String email;

  private String password;

  private String phoneNumber;

  private String name;

  private Role role;

  public User() {
  }

  @Builder
  public User(String email, String password, String phoneNumber, String name,
      Role role) {
    this.email = email;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.name = name;
    this.role = role;
  }

  public void setId(Long id) {
    this.id = id;
  }
}
