package com.example.freshcart.redis;

import com.example.freshcart.redis.infrastructure.RedisHashLoginUser;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface SessionRedisRepository extends CrudRepository<RedisHashLoginUser, String> {

  @Override
  RedisHashLoginUser save(RedisHashLoginUser user);

  @Override
  List<RedisHashLoginUser> findAll();

  RedisHashLoginUser findBySessionId(String value);
}
