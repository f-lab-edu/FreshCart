package com.example.freshcart.domain;

/**
 * 회원 가입 시 패스워드를 암호화 하여 저장.
 * 패스워드 암호화 로직이 변경될 수 있으므로, 인터페이스 의존하도록 설계.
 */
public interface PasswordEncoder {

  String encrypt(String password);

  Boolean isMatch(String candidatePassword, String storedPassword);
}
