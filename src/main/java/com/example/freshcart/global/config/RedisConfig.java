package com.example.freshcart.global.config;

import com.example.freshcart.global.domain.SessionManager;
import com.example.freshcart.global.infra.RedisSessionManager;
import com.example.freshcart.global.infra.SessionRedisRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;

/**
 * Spring Boot 2.0 부터 Jedis 가 기본 클라이언트에서 deprecated 되고 Lettuce 가 탑재 Lettuce의 장점: Connection 수를 제한할 수
 * 있다 (커넥션 수가 늘어남에 따라서 제한해야 하는 필요성 생김)
 * <p>
 * Jedis는 ThreadSafe 하지 않다. 따라서 Jedis를 멀티 쓰레드 환경에서 사용하려면 Connection pooling을 사용해야하는데, Jedis를 사용하는 동시
 * 쓰레드는 각자 고유의 Jedis 인스턴스를 가지고 있어야 하고/인스턴스 별 커넥션을 맺어야 함 반면, Lettuce는 쓰레드 끼리 커넥션 공유 가능해서 커넥션을 하나만
 * 사용해서 더 효율적이다.
 */
@Configuration
@EnableRedisRepositories
public class RedisConfig {

  @Value("${spring.redis.host}")
  private String host;
  @Value("${spring.redis.port}")
  private int port;

  @Bean
  public RedisConnectionFactory redisConnectionFactory() {
    return new LettuceConnectionFactory(host, port);
  }

  @Bean
  public SessionManager sessionManager(SessionRedisRepository redisRepository) {
    return new RedisSessionManager(redisRepository);
  }

//  @Bean
//  public SessionManager sessionManager() {
//    return new InMemorySessionManager();
//  }
}
