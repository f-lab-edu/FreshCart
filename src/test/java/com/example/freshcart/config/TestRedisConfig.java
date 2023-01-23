package com.example.freshcart.config;

import org.springframework.boot.test.context.TestConfiguration;
import redis.embedded.RedisServer;

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
