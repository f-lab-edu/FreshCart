package com.example.freshcart.user.domain;

import java.util.List;

/**
 * DB가 정해지지 않았으므로 인터페이스에 의존하게 구현.
 */
public interface UserRepository {

  User save(User user);

  User findByUserEmail(String email);

  User findById(Long id);

  List<User> findAll();

}
