package com.example.freshcart.user.presentation.web;

import com.example.freshcart.user.application.LoginUser;
import com.example.freshcart.user.infrastructure.exception.UnauthorizedRequestException;
import com.example.freshcart.user.presentation.SessionManager;
import com.example.freshcart.user.domain.LoginCheck;
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

  /**
   * preHandler의 응답값이 true 이면, 다음으로 진행한다. false 이면, 다른 인터셉터, 핸들러 어댑터 등이 호출되지 않는다. preHandler만 호출되고
   * 끝남. Product 등록은 Seller만 가능하기 때문에 (1) 로그인인지 먼저 체크하고, (2) user 객체의 role 이 seller인지 확인.
   */
  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    HandlerMethod method = (HandlerMethod) handler;

    if (method.getMethodAnnotation(LoginCheck.class) != null) {
      authenticateLogin(request);
    }
    return true;
  }


  public LoginUser authenticateLogin(HttpServletRequest request) {
    String requestURI = request.getRequestURI();
    log.info(requestURI + "인터셉터 - 인증 체크 적용");
    LoginUser user = sessionManager.getSession(request);
    if (user == null) {
      log.info("인터셉터 - 유저 정보가 없습니다" + requestURI);
      throw new UnauthorizedRequestException();
    }
    return user;
  }
}