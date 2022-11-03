package com.example.freshcart.user.application;

import com.example.freshcart.global.domain.Role;
import java.io.Serializable;
import java.time.LocalDateTime;
import org.springframework.data.annotation.Id;

/**
 * 용도: 장소에서 조회한 유저의 email, Role 정보. 서비스 이용 시 권한 확인 위함
 * RedisRepository아닌 RedisTemplate 사용 시 LoginUser 형태로 저장
 */

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

  public Long getUserId() {
    return userId;
  }

  public String getSessionId() {
    return sessionId;
  }

  public String getEmail() {
    return email;
  }

  public Role getRole() {
    return role;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public String getId() {
    return id;
  }

  public void setSessionId(String sessionId) {
    this.sessionId = sessionId;
  }
}
