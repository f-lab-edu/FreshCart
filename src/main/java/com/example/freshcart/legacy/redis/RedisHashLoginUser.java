package com.example.freshcart.legacy.redis;

import com.example.freshcart.authentication.Role;
import java.time.LocalDateTime;
import lombok.Getter;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.redis.core.index.Indexed;

/**
 *
 * 용도: 장소에서 조회한 유저의 email, Role 정보. 서비스 이용 시 권한 확인 위함 Redis Repository로 이용하기 위해서는 @Redishash 어노테이션을
 * 이용해 key를 설정해줘야함.
 *
 * 최종적으로 Redis에 들어가는 key는 @RedisHash의 value + @Id가 붙어있는 맴버변수
 * @Indexed: 사용자 ID 로 조회 하기 위함.
 *
 * * Spring Data Redis 의 Redis Repository 를 이용하면 간단하게 Domain Entity 를 Redis Hash 로 만들 수 있음. * 다만
 * 트랜잭션을 지원하지 않기 때문에 만약 트랜잭션을 적용하고 싶다면 RedisTemplate 을 사용해야 합니다.
 */

@Getter
@RedisHash(value = "LoginUser", timeToLive = 120)
public class RedisHashLoginUser {

  @Id
  private String id;
  @Indexed
  private String sessionId;
  private Long userId;
  private String email;
  private Role role;
  private LocalDateTime createdAt;


  public RedisHashLoginUser(String sessionId, Long userId, String email,
      Role role, LocalDateTime createdAt) {
    this.sessionId = sessionId;
    this.userId = userId;
    this.email = email;
    this.role = role;
    this.createdAt = createdAt;
  }

  public static RedisHashLoginUser of(String sessionId, Long userId, String email, Role role,
      LocalDateTime createdAt) {
    return new RedisHashLoginUser(sessionId, userId, email, role, createdAt);
  }
}

