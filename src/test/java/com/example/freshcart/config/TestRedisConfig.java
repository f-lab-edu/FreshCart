package com.example.freshcart.config;

import org.springframework.boot.test.context.TestConfiguration;
import redis.embedded.RedisServer;
import org.springframework.stereotype.Component;

/**
 * RedisClusterConfiguration 에 맞게 테스트 코드 작성 필요
 * 현재는 standAlone에 맞게 구성됨.
 */
@TestConfiguration
public class TestRedisConfig {

  private final RedisServer redisServer;

  public TestRedisConfig() {
    this.redisServer = RedisServer.builder()
        .port(6379)
        .setting("maxmemory 128M")
        .build();
  }
}
