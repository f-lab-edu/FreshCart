package com.example.freshcart.presentation.web;

import com.example.freshcart.application.LoginUser;
import com.example.freshcart.infrastructure.exception.UnauthorizedRequestException;
import com.example.freshcart.presentation.SessionManager;
import com.example.freshcart.presentation.web.argumentresolver.LoginCheck;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

/**
 * 로그인 인터셉터 - @LoginCheck 가 붙은(인증을 거쳐야 하는 요청이) 인증을 거치도록 강제
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
    HandlerMethod method = (HandlerMethod) handler;
    if (method.getMethodAnnotation(LoginCheck.class) != null) {
      authenticateLogin(request);
    }
    return true;
  }

  public boolean authenticateLogin(HttpServletRequest request) {
    String requestURI = request.getRequestURI();
    log.info(requestURI + "인터셉터 - 인증 체크 적용");
    LoginUser user = sessionManager.getSession(request);
    if (user == null) {
      log.info("인터셉터 - 유저 정보가 없습니다" + requestURI);
      throw new UnauthorizedRequestException();
    }
    return true;
  }
}
