package com.example.freshCart.domain;

import lombok.NonNull;

/*
@NonNull: 필드가 Null일 경우 NullPointerException 발생.
Role (1) 기본값 - USER (2) ADMIN, SELLER는 따로 값을 받을 방법을 찾아보기.
 */

public class User {

    private Long id;

    @NonNull
    private String email;

    @NonNull
    private String password;

    @NonNull
    private String name;

    private Role role;

    //입력 안할 경우 User Role로 가입.
    public User(@NonNull String email, @NonNull String password,
            @NonNull String name) {
        this.email = email;
        this.password = password;
        this.name = name;
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

}
