package com.example.freshcart.presentation.web.argumentresolver;

import com.example.freshcart.application.LoginUser;
import com.example.freshcart.presentation.SessionManager;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 로그인 객체를 보다 편리하게 가져올 수 있음.
 */

@Slf4j
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

  private SessionManager sessionManager;

  public LoginUserArgumentResolver(SessionManager sessionManager) {
    this.sessionManager = sessionManager;
  }

  /**
   * parameter 가 annotation을 갖고 있는지 확인
   */

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    log.info("supportsParameter를 시작합니다");
    boolean hasLoginAnnotation = parameter.hasParameterAnnotation(AuthenticatedUser.class);
    return hasLoginAnnotation;
  }

  /**
   * 컨트롤러 호출 직전에 호출되어서 필요한 파라미터 정보 생성. 세션에 로그인 회원 정보인 LoginUser 객체가 있다면 반환해준다. 이후 여기서 반환된 user 객체를
   * 필요할때마다 파라미터에 전달함.
   */

  @Override
  public Object resolveArgument(MethodParameter parameter, ModelAndViewContainer mavContainer,
      NativeWebRequest webRequest, WebDataBinderFactory binderFactory) throws Exception {
    HttpServletRequest request = (HttpServletRequest) webRequest.getNativeRequest();
    LoginUser user = sessionManager.getSession(request);
    if (user == null) {
      log.info("user가 없습니다");
      return null;
    }
    return user;
  }
}
