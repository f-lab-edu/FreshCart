package com.example.freshcart.global.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;

/**
 * 클래스의 목적이 Serialization/Deserialization 이므로 RedisObjectMapper 로 변경.
 */

public class RedisObjectMapper {

  private static Logger log = LoggerFactory.getLogger(RedisObjectMapper.class);
  private final RedisTemplate<String, Object> redisTemplate;
  private static ObjectMapper objectMapper = new ObjectMapper();


  public RedisObjectMapper(
      RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }


  public <T> boolean saveData(String key, T data) {

    try {
      this.objectMapper.registerModule(new JavaTimeModule());
      String value = this.objectMapper.writeValueAsString(data);
      redisTemplate.opsForValue()
          .set(key, value, SessionRedisTemplate.TimeToLive, TimeUnit.MINUTES);
      return true;
    } catch (JsonProcessingException e) {
      log.error("JsonProcessingException", e);
      return false;
    }
  }

  /**
   * writeValueAsString으로 직렬화 하여 저장. ReadValue()로 String 형태의 JSON 데이터를 JSON으로 Deserialize
   */
  public <T> T getData(String key, Class<T> classType) {
    try {
      String jsonResult = (String) redisTemplate.opsForValue().get(key);
      T object = this.objectMapper.readValue(jsonResult, classType);
      return object;

    } catch (JsonProcessingException e) {
      log.error("JsonProcessingException", e);
      return null;
    }
  }
}
