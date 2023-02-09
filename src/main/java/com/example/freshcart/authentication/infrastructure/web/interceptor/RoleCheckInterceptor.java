package com.example.freshcart.authentication.infrastructure.web.interceptor;

import com.example.freshcart.product.domain.exception.NotSellerException;
import com.example.freshcart.authentication.Role;
import com.example.freshcart.authentication.annotation.Authentication;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.example.freshcart.authentication.application.SessionManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.HandlerInterceptor;

@Slf4j
public class RoleCheckInterceptor implements HandlerInterceptor {

  private SessionManager sessionManager;

  public RoleCheckInterceptor(SessionManager sessionManager) {
    this.sessionManager = sessionManager;
  }

  @Override
  public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
      throws Exception {
    //Object handler는 handlerMethod가 아닌 것도 있다. 그럴 때 캐스팅 에러 방지를 위해 true 로 반환
    if (!(handler instanceof HandlerMethod)) {
      return true;
    }
    //handler를 handlerMethod 타입으로 다운 캐스팅.
    HandlerMethod method = (HandlerMethod) handler;

    //메서드에 붙은 어노테이션 참조
    //Authentication 어노테이션이 없다면 RoleCheckInterCeptor 를 통과할 필요가 없으니 true 로 반환.
    Authentication authentication = method.getMethodAnnotation(Authentication.class);
    if (authentication == null) {
      log.info("@Authentication이 없으므로 RoleCheckInterCeptor 를 통과할 필요가 없습니다");
      return true;
    }

    Role userAuthority = sessionManager.getSession(request).getRole();

    //현재 권한 체크는 어노테이션의 authority 값이 admin 인 경우를 확인해준다.
    if (authentication.authority().equals(Role.SELLER)) {
      if (userAuthority.equals(Role.SELLER)) {
        return true;
      }
    }
    throw new NotSellerException();
  }
}

