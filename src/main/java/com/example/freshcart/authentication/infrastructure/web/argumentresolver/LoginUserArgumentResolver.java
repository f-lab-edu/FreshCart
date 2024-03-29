package com.example.freshcart.authentication.infrastructure.web.argumentresolver;

import com.example.freshcart.authentication.annotation.AuthenticatedUser;
import com.example.freshcart.authentication.application.LoginUser;
import com.example.freshcart.authentication.application.SessionManager;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.web.bind.support.WebDataBinderFactory;
import org.springframework.web.context.request.NativeWebRequest;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.method.support.ModelAndViewContainer;

/**
 * 로그인 객체를 보다 편리하게 가져올 수 있음.
 * AuthenticatedUser.Class가 parameter 로 존재하는 컨트롤러에 resolveArgument를 진행.
 */

@Slf4j
public class LoginUserArgumentResolver implements HandlerMethodArgumentResolver {

  private SessionManager sessionManager;

  public LoginUserArgumentResolver(SessionManager sessionManager) {
    this.sessionManager = sessionManager;
  }

  /**
   * ArgumentResolver가 해당 파라미터를 지원하는지 체크 (
   */

  @Override
  public boolean supportsParameter(MethodParameter parameter) {
    log.info("supportsParameter를 시작합니다");
    boolean hasLoginAnnotation = parameter.hasParameterAnnotation(AuthenticatedUser.class);
    return hasLoginAnnotation;
  }

  /**
   * 컨트롤러 호출 직전에 호출되어서 필요한 파라미터 정보 생성. 세션에 로그인 회원 정보인 LoginUser 객체가 있다면 반환해준다. 이후 여기서 반환된 user 객체를
   * 필요할때마다 파라미터에 전달함. 인증/인가가 필요할 때, Session (추후에는 토큰)에서 user_id를 꺼내서 써야함. 이렇게 세션의 정보를 통해서 기능이
   * 이루어져야, 행위 부정이 일어나지 않기 때문.
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
    log.info(user.getEmail() + "resolve argument 통과");

    return user;
  }
}
