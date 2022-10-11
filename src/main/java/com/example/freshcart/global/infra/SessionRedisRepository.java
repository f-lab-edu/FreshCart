package com.example.freshcart.global.infra;

import com.example.freshcart.user.application.RedisHashLoginUser;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface SessionRedisRepository extends CrudRepository<RedisHashLoginUser, String> {

  @Override
  RedisHashLoginUser save(RedisHashLoginUser user);

  @Override
  List<RedisHashLoginUser> findAll();

  Optional<RedisHashLoginUser> findBySessionId(String value);
}
