package com.example.freshcart.user.infrastructure;

import com.example.freshcart.user.domain.User;
import java.util.List;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {

  void insert(User user);

  User findByUserEmail(String email);

  List<User> findAll();
}
