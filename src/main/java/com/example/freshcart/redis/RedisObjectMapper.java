package com.example.freshcart.redis;

import com.example.freshcart.authentication.application.LoginUser;
import com.example.freshcart.global.exception.UnauthorizedRequestException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 클래스의 목적이 Serialization/Deserialization 이므로 RedisObjectMapper 로 클래스명 변경. writeValueAsString으로 직렬화
 * 하여 저장. ReadValue()로 String 형태의 JSON 데이터를 JSON으로 Deserialize
 */

public class RedisObjectMapper {

  private static Logger log = LoggerFactory.getLogger(RedisObjectMapper.class);
  private static ObjectMapper objectMapper = new ObjectMapper();
  private final RedisTemplate<String, String> redisTemplate;

  public RedisObjectMapper(
      RedisTemplate<String, String> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }

  public <T> boolean saveData(String key, T data) {

    try {
      objectMapper.registerModule(new JavaTimeModule());
      String value = objectMapper.writeValueAsString(data);
      redisTemplate.opsForValue()
          .set(key, value, SessionRedisTemplate.TIME_TO_LIVE, TimeUnit.MINUTES);
      return true;
    } catch (JsonProcessingException e) {
      log.error("JsonProcessingException", e);
      return false;
    }
  }

  public LoginUser getData(String sessionId) throws JsonProcessingException {
    //objectMapper가 null을 handle 하지 못하기 때문에, string으로 null인지 먼저 체크 필요함.
      String jsonResult = redisTemplate.opsForValue().get(sessionId);
      if (jsonResult == null) {
        throw new UnauthorizedRequestException();
      }
      LoginUser user = objectMapper.readValue(jsonResult, LoginUser.class);
      return user;
  }


  public void removeData(String sessionId) {
    redisTemplate.delete(sessionId);
  }
}
