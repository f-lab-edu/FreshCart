package com.example.freshCart.infrastructure.config;

import com.example.freshCart.application.UserService;
import com.example.freshCart.infrastructure.BCryptEncoder;
import com.example.freshCart.domain.PasswordEncoder;
import com.example.freshCart.domain.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
