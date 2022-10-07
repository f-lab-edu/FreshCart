package com.example.freshcart.user.application;

import com.example.freshcart.user.presentation.request.LoginRequest;
import com.example.freshcart.user.presentation.request.SignupRequest;
import com.example.freshcart.user.domain.PasswordEncoder;
import com.example.freshcart.user.domain.User;
import com.example.freshcart.user.domain.UserRepository;
import com.example.freshcart.user.infrastructure.exception.EmailExistsException;
import com.example.freshcart.user.infrastructure.exception.PasswordDoesNotMatchException;
import com.example.freshcart.user.infrastructure.exception.UserNotExistsException;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * 회원 가입, 로그인 로직 실행.
 */
public class UserService {

  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public User register(SignupRequest request) {
    // 이메일 중복 체크
    if (userRepository.findByUserEmail(request.getEmail()) != null) {
      throw new EmailExistsException();
    }

    String password = request.getPassword();
    // passwordEncoder로 암호화된 비밀번호를 리턴
    User user =
        new User(
            request.getEmail(),
            passwordEncoder.encrypt(password),
            request.getPhoneNumber(),
            request.getName(),
            request.getRole());
    return userRepository.save(user);
  }

  public LoginUser signIn(LoginRequest request) {
    User emailMatched = userRepository.findByUserEmail(request.getEmail());
    if (emailMatched == null) {
      throw new UserNotExistsException();
    }
    boolean passwordMatch = passwordEncoder.isMatch(request.getPassword(),
        emailMatched.getPassword());
    if (!passwordMatch) {
      throw new PasswordDoesNotMatchException();
    }

    //세션을 저장할 때, 세션의 Id로 쓰일 값.
    String sessionId = UUID.randomUUID().toString();
    LoginUser user = LoginUser.of(sessionId, emailMatched.getUserId(), emailMatched.getEmail(),
        emailMatched.getRole(), LocalDateTime.now());
    return user;
  }
}
