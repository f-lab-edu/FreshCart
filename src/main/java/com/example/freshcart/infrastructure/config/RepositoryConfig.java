package com.example.freshcart.infrastructure.config;

import com.example.freshcart.domain.UserRepository;
import com.example.freshcart.infrastructure.UserInMemoryRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 인터페이스 생성 시 구현체를 등록할 수 있도록 설정.
 */

@Configuration
public class RepositoryConfig {

  @Bean
  public UserRepository userRepository() {
    return new UserInMemoryRepository();
  }

}
