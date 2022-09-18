package com.example.freshcart.infrastructure;

import com.example.freshcart.domain.PasswordEncoder;
import org.mindrot.jbcrypt.BCrypt;

/**
 * 로그인 시도 시, 이메일이 저장소에 존재하지 않을 경우 예외.
 * 해시의 단점을 보완하기 위해서, 패스워드 마다 고유 솔트를 가질 수 있도록 처리.
 */

public class BCryptEncoder implements PasswordEncoder {

  public BCryptEncoder() {
  }

  @Override
  public String encrypt(String password) {
    String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt());
    return hashPassword;
  }

  @Override
  public Boolean isMatch(String candidatePassword, String storedPassword) {
    Boolean result = BCrypt.checkpw(candidatePassword, storedPassword);
    return result;
  }
}
