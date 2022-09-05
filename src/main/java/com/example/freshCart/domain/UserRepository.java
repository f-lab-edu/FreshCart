package com.example.freshCart.domain;

import java.util.List;
import java.util.Optional;

public interface UserRepository {
  User save(User user);

  void findEmailDuplicate(String email);

  Optional<User> findByUserEmail(String email);

  List<User> findAll();
}
