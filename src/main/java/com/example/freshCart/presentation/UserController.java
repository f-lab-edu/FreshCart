package com.example.freshCart.presentation;

import com.example.freshCart.application.UserService;
import com.example.freshCart.application.*;
import com.example.freshCart.application.command.LoginCommand;
import com.example.freshCart.application.command.SignupCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

  @PostMapping("/logout")
  public void logout(HttpServletRequest request) {
    sessionManager.expireSession(request);
  }

  // 인터셉터 작동 여부 확인 - 홈 화면
  @GetMapping("/home")
  public String homeLogin() {
    return "환영합니다.";
  }

  @GetMapping("/unexpected")
  public String unExpectedException() {
    throw new IllegalCallerException();
  }
}
