package com.example.freshCart.domain;

public interface PasswordEncoder {

  String encrypt(String password);

  Boolean isMatch(String candidatePassword, String storedPassword);
}
