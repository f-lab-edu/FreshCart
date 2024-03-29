package com.example.freshcart.redis.config;

import com.example.freshcart.authentication.application.SessionManager;
import com.example.freshcart.legacy.redis.RedisObjectMapper;
import com.example.freshcart.redis.config.RedisPropertiesConfig.RedisProperties;
import com.example.freshcart.redis.infrastructure.RedisSessionTemplateManager;
import com.example.freshcart.redis.infrastructure.SessionRedisTemplate;
import io.lettuce.core.ReadFrom;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisClusterConfiguration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.connection.lettuce.LettuceClientConfiguration;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.repository.configuration.EnableRedisRepositories;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * Spring Boot 2.0 부터 Jedis 가 기본 클라이언트에서 deprecated 되고 Lettuce 가 탑재 Lettuce의 장점: Connection 수를 제한할 수
 * 있다 (커넥션 수가 늘어남에 따라서 제한해야 하는 필요성 생김)
 * <p>
 * Jedis는 ThreadSafe 하지 않다. 따라서 Jedis를 멀티 쓰레드 환경에서 사용하려면 Connection pooling을 사용해야하는데, Jedis를 사용하는 동시
 * 쓰레드는 각자 고유의 Jedis 인스턴스를 가지고 있어야 하고/인스턴스 별 커넥션을 맺어야 함 반면, Lettuce는 쓰레드 끼리 커넥션 공유 가능해서 커넥션을 하나만
 * 사용해서 더 효율적이다.
 *
 * <RedisTemplate>
 * Serialize 선택 옵션 1) Jackson2JsonRedisSerializer(classType.class): classType을 Json 형태로 저장. 특정
 * 클래스에게만 직속되어 있음. classtype마다 redisTemplate을 따로 생성해야 함. 2) GenericJackson2JsonRedisSerializer: 모든
 * classType을 json 형태로 저장할 수 있는 범용적인 Jackson2JsonRedisSerializer이다. 객체에 클래스 타입도 저장된다는 단점이 있지만 다양한 타입
 * 객체를 사용 할 때 사용하기에 좋다 deserialize 할 때 @class 필드를 참조하는데, 패키지를 이동시켰을 때 이동하기 전 클래스를 deserialize 할때,
 * classNotFound 예외를 터뜨릴 수 있다. 따라서 패키지 이동을 주의해야 함. 3)
 */
@Configuration
@EnableRedisRepositories
@RequiredArgsConstructor
public class RedisConfig {

  private final RedisProperties properties;

  @Bean
  public RedisConnectionFactory redisConnectionFactory() {
    LettuceClientConfiguration clientConfiguration = LettuceClientConfiguration.builder()
        .readFrom(ReadFrom.REPLICA_PREFERRED)
        .build();
    RedisClusterConfiguration redisClusterConfig = new RedisClusterConfiguration(properties.getNodes());
    return new LettuceConnectionFactory(redisClusterConfig, clientConfiguration);
  }
//  주석 처리를 지양하고자 하나, 다른 구현체로 변경할 경우에 대비해서 주석 처리함.
//  @Bean
//  public SessionManager sessionManager(SessionRedisRepository redisRepository) {
//    return new RedisSessionManager(redisRepository);
//  }

//  @Bean
//  public SessionManager sessionManager() {
//    return new InMemorySessionManager();
//  }


  @Bean
  public SessionManager sessionManager(SessionRedisTemplate sessionRedisTemplate) {
    return new RedisSessionTemplateManager(sessionRedisTemplate);
  }

  @Bean
  public SessionRedisTemplate sessionRedisTemplate(RedisObjectMapper redisObjectMapper) {
    return new SessionRedisTemplate(redisObjectMapper);
  }

  @Bean
  public RedisObjectMapper redisObjectMapper(RedisTemplate<String, String> redisTemplate) {
    return new RedisObjectMapper(redisTemplate);
  }

  @Bean
  public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory connectionFactory) {
    RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(connectionFactory);
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(new StringRedisSerializer());
    return redisTemplate;
  }

}
