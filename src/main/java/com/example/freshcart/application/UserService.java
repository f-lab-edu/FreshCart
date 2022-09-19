package com.example.freshcart.application;

import com.example.freshcart.application.command.LoginCommand;
import com.example.freshcart.application.command.SignupCommand;
import com.example.freshcart.domain.PasswordEncoder;
import com.example.freshcart.domain.User;
import com.example.freshcart.domain.UserRepository;
import com.example.freshcart.infrastructure.exception.EmailExistsException;
import com.example.freshcart.infrastructure.exception.PasswordDoesNotMatchException;
import com.example.freshcart.infrastructure.exception.UserNotExistsException;

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

  public void register(SignupCommand request) {
    // 이메일 중복 체크
    String email = request.getEmail();
    if (userRepository.findEmailDuplicate(email) != null) {
      throw new EmailExistsException();
    }
    // 해결 중: enum에 정해진 값 외의 값이 들어올 경우, 서비스 로직이 실행되지 않음.
    //    if(Role.enumCheck(request.getRole().name())==null) {
    //      throw new EnumNotPresentException();
    //    }
    String password = request.getPassword();
    // passwordEncoder로 암호화된 비밀번호를 리턴
    User user =
        new User(
            request.getEmail(),
            passwordEncoder.encrypt(password),
            request.getPhoneNumber(),
            request.getName(),
            request.getRole());
    userRepository.save(user);
  }

  public LoginUser signIn(LoginCommand request) {
    User emailMatched = userRepository.findByUserEmail(request.getEmail()).orElse(null);
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
