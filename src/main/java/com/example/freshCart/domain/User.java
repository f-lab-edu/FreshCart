package com.example.freshCart.domain;

import javax.validation.constraints.NotNull;

/*
@NotNull + Valid(@RequestBody와 함께) 쓰면, 필드를 체크해준다.
 */

public class User {

    private Long id;

    private String email;

    private String password;

    private String name;

    private Role role;

  // 입력 안할 경우 User Role로 가입.

  public User(String email, String password, String name, Role role) {
        this.email = email;
        this.password = password;
        this.name = name;
    this.role = role;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
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
