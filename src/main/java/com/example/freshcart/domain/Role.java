package com.example.freshcart.domain;

/**
 * 가입 유형: 유저, 판매자, 관리자
 */
public enum Role {
  USER, SELLER, ADMIN;

  public static Role search(String columnName) {
    for (Role element : Role.values()) {
      if (element.name().equalsIgnoreCase(columnName)) {
        return element;
      }
    }
    return null;
  }

  public static Role enumCheck(String name) {
    Role result = null;
    for (Role role : values()) {
      //일치한다면 반복문을 빠져나올 수 있다.
      if (role.name().equalsIgnoreCase(name)) {
        result = role;
        break;
      }
    }
    return result;
  }
}
