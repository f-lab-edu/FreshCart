package com.example.freshcart.infrastructure.config;

import com.example.freshcart.domain.UserRepository;
import com.example.freshcart.infrastructure.UserMapper;
import com.example.freshcart.infrastructure.UserMapperRepositoryAdaptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 인터페이스 생성 시 구현체를 등록할 수 있도록 설정.
 */

@Configuration
public class RepositoryConfig {

  @Bean
  public UserRepository userRepository(UserMapper userMapper) {
    return new UserMapperRepositoryAdaptor(userMapper);
  }
/**
 * InMemoryRepo 사용 시 주석 제거하여 사용
 */

//  @Bean
//  public UserRepository userRepository() {
//  return new UserInMemoryRepository();
//  }
}

