package com.example.freshcart.presentation;

import com.example.freshcart.application.LoginUser;
import com.example.freshcart.application.UserService;
import com.example.freshcart.application.command.LoginCommand;
import com.example.freshcart.application.command.SignupCommand;
import com.example.freshcart.presentation.web.argumentresolver.AuthenticatedUser;
import com.example.freshcart.presentation.web.argumentresolver.LoginCheck;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/users")
public class UserController {

  private UserService userService;
  private SessionManager sessionManager;

  public UserController(UserService userService, SessionManager sessionManager) {
    this.userService = userService;
    this.sessionManager = sessionManager;
  }

  /*
  회원 가입
   */
  @PostMapping("/signup")
  public void signup(@Valid @RequestBody SignupCommand request) {
    userService.register(request);
  }

  // 로그인 실패 시 예외처리가 되고, 성공 시 loginUser (ID, PW만 포함) 이 리턴됨.
  @PostMapping("/login")
  public void login(@RequestBody LoginCommand request, HttpServletResponse servletResponse) {
    LoginUser check = userService.signIn(request);
    sessionManager.createSession(check, servletResponse);
  }

  @LoginCheck
  @PostMapping("/logout")
  public void logout(HttpServletRequest request) {
    sessionManager.expireSession(request);
  }

  //LoginCheck가 요구되는 로직 테스트용 - 추후 기능 개발 시 삭제 예정
  @LoginCheck
  @PostMapping("/logincheck")
  public String test() {
    return "LoginCheck 테스트 중";
  }

  @LoginCheck
  @GetMapping("/home")
  public String homeLogin(@AuthenticatedUser LoginUser user) {
    log.info(user.getEmail() + "로그인된 유저임을 확인");
    return "환영합니다.";
  }

  //unexpected 에러 발생 시 테스트용 확인 - 추후 기능 개발 시 삭제 예정
  @GetMapping("/unexpected")
  public String unExpectedException() {
    throw new IllegalCallerException();
  }
}