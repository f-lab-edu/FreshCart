package com.example.freshcart.application;

import com.example.freshcart.application.command.LoginCommand;
import com.example.freshcart.application.command.SignupCommand;
import com.example.freshcart.domain.PasswordEncoder;
import com.example.freshcart.domain.Role;
import com.example.freshcart.domain.User;
import com.example.freshcart.domain.UserRepository;
import com.example.freshcart.infrastructure.exception.EmailExistsException;
import com.example.freshcart.infrastructure.exception.EnumNotPresentException;
import com.example.freshcart.infrastructure.exception.PasswordDoesNotMatchException;
import com.example.freshcart.infrastructure.exception.UserNotExistsException;
import java.util.stream.Stream;

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

  public User register(SignupCommand request) {
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

  public LoginUser signIn(LoginCommand request) {
    User emailMatched = userRepository.findByUserEmail(request.getEmail());
    if (emailMatched == null) {
      throw new UserNotExistsException();
    }
    LoginUser user = LoginUser.of(emailMatched.getEmail(), emailMatched.getRole());
    Boolean passwordMatch = passwordEncoder.isMatch(request.getPassword(),
        emailMatched.getPassword());
    if (!passwordMatch) {
      throw new PasswordDoesNotMatchException();
    }
    return user;
  }
}
