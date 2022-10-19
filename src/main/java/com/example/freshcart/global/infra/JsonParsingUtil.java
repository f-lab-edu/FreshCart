package com.example.freshcart.global.infra;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;


public class JsonParsingUtil {

  private static Logger log = LoggerFactory.getLogger(JsonParsingUtil.class);
  private final RedisTemplate<String, Object> redisTemplate;

  public JsonParsingUtil(
      RedisTemplate<String, Object> redisTemplate) {
    this.redisTemplate = redisTemplate;
  }


  public <T> boolean saveData(String key, T data) {

    try {
      ObjectMapper mapper = new ObjectMapper();
      mapper.registerModule(new JavaTimeModule());
      String value = mapper.writeValueAsString(data);
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
      ObjectMapper mapper = new ObjectMapper();
      mapper.registerModule(new JavaTimeModule());
      T object = mapper.readValue(jsonResult, classType);
      return object;

    } catch (JsonProcessingException e) {
      log.error("JsonProcessingException", e);
      return null;
    }
  }
}
