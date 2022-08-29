package com.example.freshCart.config;

import com.example.freshCart.application.UserRegisterServiceV1;
import com.example.freshCart.domain.BCryptEncoder;
import com.example.freshCart.domain.PasswordEncoder;
import com.example.freshCart.domain.UserRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceConfig {

    @Bean
    public UserRegisterServiceV1 userService(UserRepository userRepository, PasswordEncoder passwordEncoder){
        return new UserRegisterServiceV1(userRepository, passwordEncoder);
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptEncoder();
    }
}
