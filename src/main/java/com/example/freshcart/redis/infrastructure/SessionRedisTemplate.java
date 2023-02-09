package com.example.freshcart.redis.infrastructure;

import com.example.freshcart.authentication.application.LoginUser;
import com.example.freshcart.global.exception.JsonDeserializationIssueException;
import com.example.freshcart.legacy.redis.RedisObjectMapper;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * SessionRedisRepository, SessionRedisTemplate 을 구현체로 만들고 인터페이스에 의존하고자 했으나, Repository 가 인터페이스여야 해서
 * 불가함.
 */

public class SessionRedisTemplate {

  public static final long TIME_TO_LIVE = 10; // 테스트 용. 10분으로 세션 유효기간 설정.
  private RedisObjectMapper redisObjectMapper;


  public SessionRedisTemplate(RedisObjectMapper redisObjectMapper) {
    this.redisObjectMapper = redisObjectMapper;
  }

  public void save(LoginUser loginUser) {
    redisObjectMapper.saveData(loginUser.getSessionId(), loginUser);
  }


  public LoginUser findBySessionId(String sessionId) {
    try {
      return redisObjectMapper.getData(sessionId);
    } catch (JsonProcessingException e) {
      throw new JsonDeserializationIssueException();
    }
  }

  public void remove(String sessionCookie) {
    redisObjectMapper.removeData(sessionCookie);
  }

}
