package com.example.freshCart.presentation.web;

import com.example.freshCart.application.LoginUser;
import com.example.freshCart.presentation.SessionManager;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.servlet.HandlerInterceptor;

/*
1. 로그를 찍어서 어떤 uri 가 인터셉터에 접근하는지 확인.
2.
 */

@Slf4j
public class LoginCheckInterceptor implements HandlerInterceptor {

  private SessionManager sessionManager;

  public LoginCheckInterceptor(SessionManager sessionManager) {
    this.sessionManager = sessionManager;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {

    String requestURI = request.getRequestURI();
    log.info(requestURI + "인증 체크 인터셉터 실행");

    //    HttpSession session = request.getSession(false); 위와 같이 Session 객체를 직접 생성하는 대신,
    // sessionManager에서 생성
    LoginUser user = sessionManager.getSession(request);
    if (user == null) {
      log.info("인증되지 않은 요청" + requestURI);
      response.sendRedirect("/users/login?redirectURL=" + requestURI);
      return false;
    }
    return true;
  }
}
