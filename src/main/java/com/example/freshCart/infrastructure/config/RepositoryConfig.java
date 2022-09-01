package com.example.freshCart.infrastructure.config;

import com.example.freshCart.domain.UserRepository;
import com.example.freshCart.infrastructure.UserInMemoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RepositoryConfig {

    @Bean
    public UserRepository userRepository(){
        return new UserInMemoryRepository();
    }
}
