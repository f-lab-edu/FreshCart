package com.example.freshcart.infrastructure.config;

import com.example.freshcart.domain.UserRepository;
import com.example.freshcart.infrastructure.UserInMemoryRepository;
import com.example.freshcart.infrastructure.UserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 인터페이스 생성 시 구현체를 등록할 수 있도록 설정.
 */

@Configuration
public class RepositoryConfig {

  private final UserMapper userMapper;

  public RepositoryConfig(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  @Bean
  public UserRepository userRepository() {
    return new UserMapperRepositoryAdaptor(userMapper);
  //return new UserInMemoryRepository();
  }
}
