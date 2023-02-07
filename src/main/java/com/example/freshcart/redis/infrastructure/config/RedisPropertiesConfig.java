package com.example.freshcart.redis.infrastructure.config;

import com.example.freshcart.redis.infrastructure.config.RedisPropertiesConfig.RedisProperties;
import java.util.List;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConstructorBinding;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;
/**
 * @value / configurationProperties 주입 방식은 불변 보장이 되지 않습니다.
 * @ConstructorBinding 어노테이션을 이용하면 final 필드에 대해 값을 주입
 * @ConstructorBinding 스프링 2.3 이후 생성자 주입 방식으로 불변성을 가지고 Properties 만들게 추가.
 */

@Configuration
@EnableConfigurationProperties(value = {RedisProperties.class})
public class RedisPropertiesConfig {

  @Getter
  @RequiredArgsConstructor
  @ConstructorBinding
  @ConfigurationProperties(prefix = "spring.redis")
  public static class RedisProperties {
    private final List<String> nodes;
    private final String host;
    private final int port;
  }
}