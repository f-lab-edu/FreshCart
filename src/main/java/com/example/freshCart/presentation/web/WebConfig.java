package com.example.freshCart.presentation.web;

/*
 * InterceptorRegistry: 인터셉터 목록
 * addInterceptor 메서드로 인터셉터를 하나씩 등록 / 패턴 지정
 */

import com.example.freshCart.presentation.SessionManager;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer {

  private SessionManager sessionManager;

  public WebConfig(SessionManager sessionManager) {
    this.sessionManager = sessionManager;
  }

  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry
        .addInterceptor(new LoginCheckInterceptor(sessionManager))
        .order(1)
        .addPathPatterns("/**")
        .excludePathPatterns("/users/login", "/users/logout", "/users/signup", "/users/unexpected");
  }

  // argument Resolver 등록해야 함.
}
