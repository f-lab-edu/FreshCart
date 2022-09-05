package com.example.freshCart.application;

import com.example.freshCart.application.command.LoginCommand;
import com.example.freshCart.application.command.SignupCommand;
import com.example.freshCart.domain.PasswordEncoder;
import com.example.freshCart.domain.User;
import com.example.freshCart.domain.UserRepository;
import com.example.freshCart.infrastructure.exception.PasswordDoesNotMatchException;
import com.example.freshCart.infrastructure.exception.UserNotExistsException;

public class UserService {

  private UserRepository userRepository;
  private PasswordEncoder passwordEncoder;

  public UserService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    this.userRepository = userRepository;
    this.passwordEncoder = passwordEncoder;
  }

  public void register(SignupCommand request) {
    // 이메일 중복 체크
    String email = request.getEmail();
    userRepository.findEmailDuplicate(email);
    String password = request.getPassword();
    // passwordEncoder로 암호화된 비밀번호를 리턴
    User user =
        new User(
            request.getEmail(),
            passwordEncoder.encrypt(password),
            request.getName(),
            request.getRole());
    userRepository.save(user);
  }

  // Login 아이디가 일치하는 값을 먼저 찾아오고 password 비교해서 가져오기. 일치하지 않을 경우 exception 반환.
  public LoginUser signIn(LoginCommand request) {
    User emailMatched = userRepository.findByUserEmail(request.getEmail()).orElse(null);
    if (emailMatched == null) {
      throw new UserNotExistsException();
    }
    LoginUser user = LoginUser.of(emailMatched.getEmail(), emailMatched.getPassword());
    Boolean passwordMatch = passwordEncoder.isMatch(request.getPassword(), user.getPassword());
    if (!passwordMatch) {
      throw new PasswordDoesNotMatchException();
    }
    return user;
  }
}
