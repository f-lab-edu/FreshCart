package com.example.freshcart.infrastructure;

import com.example.freshcart.domain.User;
import java.util.List;
import java.util.Optional;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

  void save(User user);

  User findEmailDuplicate(String email);

  Optional<User> findByUserEmail(String email);

  List<User> findAll();
}
