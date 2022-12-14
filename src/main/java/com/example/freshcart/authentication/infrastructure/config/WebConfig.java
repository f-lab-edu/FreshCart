package com.example.freshcart.authentication.infrastructure.config;

/*
 * InterceptorRegistry: 인터셉터 목록
 * addInterceptor 메서드로 인터셉터를 하나씩 등록 / 패턴 지정
 */

import com.example.freshcart.authentication.application.SessionManager;
import com.example.freshcart.authentication.infrastructure.web.interceptor.LoginCheckInterceptor;
import com.example.freshcart.authentication.infrastructure.web.argumentresolver.LoginUserArgumentResolver;
import com.example.freshcart.authentication.infrastructure.web.interceptor.RoleCheckInterceptor;
import java.util.List;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.method.support.HandlerMethodArgumentResolver;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * 등록할 인터셉터, ArgumentResolver 등을 표시
 */

@Configuration
public class WebConfig implements WebMvcConfigurer {

  private SessionManager sessionManager;

  public WebConfig(SessionManager sessionManager) {
    this.sessionManager = sessionManager;
  }


  @Override
  public void addInterceptors(InterceptorRegistry registry) {
    registry.addInterceptor(new LoginCheckInterceptor(sessionManager));
    registry.addInterceptor(new RoleCheckInterceptor(sessionManager));
  }

  @Override
  public void addArgumentResolvers(List<HandlerMethodArgumentResolver> resolvers) {
    resolvers.add(new LoginUserArgumentResolver(sessionManager));
  }
}
