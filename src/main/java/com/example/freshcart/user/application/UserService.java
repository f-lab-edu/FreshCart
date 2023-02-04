package com.example.freshcart.user.application;

import com.example.freshcart.authentication.application.LoginUser;
import com.example.freshcart.user.application.command.LoginCommand;
import com.example.freshcart.user.application.command.SignupCommand;
import com.example.freshcart.user.domain.PasswordEncoder;
import com.example.freshcart.user.domain.User;
import com.example.freshcart.user.domain.UserRepository;
import com.example.freshcart.user.domain.exception.EmailExistsException;
import com.example.freshcart.user.domain.exception.PasswordDoesNotMatchException;
import com.example.freshcart.user.domain.exception.UserNotExistsException;
import java.time.LocalDateTime;
import org.springframework.transaction.annotation.Transactional;

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

  @Transactional
  public User register(SignupCommand command) {
    // 이메일 중복 체크
    if (userRepository.findByUserEmail(command.getEmail()) != null) {
      throw new EmailExistsException();
    }

    String password = command.getPassword();
    User user =
        new User(
            command.getEmail(),
            passwordEncoder.encrypt(password),
            command.getPhoneNumber(),
            command.getName(),
            command.getRole());
    return userRepository.save(user);
  }

  @Transactional
  public LoginUser signIn(LoginCommand command) {
    User emailMatched = userRepository.findByUserEmail(command.getEmail());
    if (emailMatched == null) {
      throw new UserNotExistsException();
    }
    boolean passwordMatch = passwordEncoder.isMatch(command.getPassword(),
        emailMatched.getPassword());
    if (!passwordMatch) {
      throw new PasswordDoesNotMatchException();
    }

    //세션을 저장할 때, 세션의 Id로 쓰일 값.
    LoginUser user = LoginUser.of("", emailMatched.getId(), emailMatched.getEmail(),
        emailMatched.getRole(), LocalDateTime.now());
    return user;
  }
}
