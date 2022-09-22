package com.example.freshcart.domain;

import java.util.List;
import java.util.Optional;

/**
 * DB가 정해지지 않았으므로 인터페이스에 의존하게 구현.
 */
public interface UserRepository {

  User save(User user);

  User findByUserEmail(String email);

  List<User> findAll();
}
