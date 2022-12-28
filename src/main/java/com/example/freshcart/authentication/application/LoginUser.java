package com.example.freshcart.authentication.application;

import com.example.freshcart.authentication.Role;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Builder;
import lombok.Getter;
import org.springframework.data.annotation.Id;

/**
 * 용도: 장소에서 조회한 유저의 email, Role 정보. 서비스 이용 시 권한 확인 위함
 * RedisRepository아닌 RedisTemplate 사용 시 LoginUser 형태로 저장
 */

@Getter
public class LoginUser implements Serializable {

  @Id
  private String id;
  private String sessionId;
  private Long userId;
  private String email;
  private Role role;
  private LocalDateTime createdAt;


  public LoginUser(String sessionId, Long userId, String email,
      Role role, LocalDateTime createdAt) {
    this.sessionId = sessionId;
    this.userId = userId;
    this.email = email;
    this.role = role;
    this.createdAt = createdAt;
  }

  @Builder
  public LoginUser(String id, String sessionId, Long userId, String email,
      Role role, LocalDateTime createdAt) {
    this.id = id;
    this.sessionId = sessionId;
    this.userId = userId;
    this.email = email;
    this.role = role;
    this.createdAt = createdAt;
  }

  public LoginUser() {
  }

  public static LoginUser of(String sessionId, Long userId, String email, Role role,
      LocalDateTime createdAt) {
    return new LoginUser(sessionId, userId, email, role, createdAt);
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }

}
