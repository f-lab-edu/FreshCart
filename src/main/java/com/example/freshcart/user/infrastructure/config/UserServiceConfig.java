package com.example.freshcart.user.infrastructure.config;


import com.example.freshcart.user.application.UserService;
import com.example.freshcart.user.domain.PasswordEncoder;
import com.example.freshcart.user.domain.UserRepository;
import com.example.freshcart.user.infrastructure.BCryptEncoder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 passwordEncoder, userService 생성 시 구현체를 등록할 수 있도록 설정.
 */

@Configuration
public class UserServiceConfig {

  @Bean
  public UserService userService(UserRepository userRepository, PasswordEncoder passwordEncoder) {
    return new UserService(userRepository, passwordEncoder);
  }

  @Bean
  public PasswordEncoder passwordEncoder() {
    return new BCryptEncoder();
  }
}
