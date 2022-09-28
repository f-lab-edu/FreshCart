package com.example.freshcart.user.domain;

import com.example.freshcart.global.domain.Role;

/**
 * NotNull + Valid(@RequestBody와 함께)로 필드가 입력되었는지 점검 .
 */

public class User {

  private Long id;

  private String email;

  private String password;

  private String phoneNumber;

  private String name;

  private Role role;

  public User() {
  }

  public User(String email, String password, String phoneNumber, String name,
      Role role) {
    this.email = email;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.name = name;
    this.role = role;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
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
