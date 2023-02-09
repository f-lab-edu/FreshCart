package com.example.freshcart.legacy.redis;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface SessionRedisRepository extends CrudRepository<RedisHashLoginUser, String> {

  @Override
  RedisHashLoginUser save(RedisHashLoginUser user);

  @Override
  List<RedisHashLoginUser> findAll();

  RedisHashLoginUser findBySessionId(String value);
}
