package com.example.freshCart.presentation;

import com.example.freshCart.application.UserRegisterService;
import com.example.freshCart.application.*;
import com.example.freshCart.application.command.LoginCommand;
import com.example.freshCart.application.command.SignupCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

  //  private AuthenticationService authentication;
  private final SessionManager sessionManager;
  private UserRegisterService userRegisterService;

  public UserController(UserRegisterService userRegisterService, SessionManager sessionManager) {
    this.userRegisterService = userRegisterService;
    this.sessionManager = sessionManager;
  }

  /*
  회원 가입
   */
  @PostMapping("/signup")
  public void signup(@RequestBody SignupCommand request) {
    userRegisterService.register(request);
  }

  /*
  exception 처리 필요.
   */

  @PostMapping("/login")
  public void login(@RequestBody LoginCommand request, HttpServletResponse servletResponse) {
    // 로그인 실패 시 예외처리가 되고, 성공 시 loginUser (ID, PW만 포함) 이 리턴됨.
    LoginUser check = userRegisterService.signIn(request);
    sessionManager.createSession(check, servletResponse);
  }

  @GetMapping("/home")
  public String homeLogin(HttpServletRequest request) {
    // 세션 매니저를 통해서 저장된 회원 정보를 조회
    LoginUser user = sessionManager.getSession(request);
    if (user == null) {
      return "세션이 존재하지 않습니다";
    } else {
      return "환영합니다." + user.getEmail(); //
    }
  }

  @PostMapping("/logout")
  public void logout(HttpServletRequest request) {
    sessionManager.expireSession(request);
  }
}