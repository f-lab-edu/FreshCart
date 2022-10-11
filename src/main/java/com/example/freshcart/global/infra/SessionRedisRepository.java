package com.example.freshcart.global.infra;

import com.example.freshcart.user.application.LoginUser;
import java.util.List;
import java.util.Optional;
import org.springframework.data.repository.CrudRepository;

public interface SessionRedisRepository extends CrudRepository<LoginUser, String> {

  @Override
  LoginUser save(LoginUser user);

  @Override
  List<LoginUser> findAll();

  Optional<LoginUser> findBySessionId(String value);
}
