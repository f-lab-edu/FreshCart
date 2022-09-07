package com.example.freshcart.infrastructure.config;


import com.example.freshcart.application.UserService;
import com.example.freshcart.domain.PasswordEncoder;
import com.example.freshcart.domain.UserRepository;
import com.example.freshcart.infrastructure.BCryptEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 passwordEncoder, userService 생성 시 구현체를 등록할 수 있도록 설정.
 */

@Configuration
public class ServiceConfig {

  @Bean
  public UserService userService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    return new UserService(userRepository, passwordEncoder);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptEncoder();
  }
}
