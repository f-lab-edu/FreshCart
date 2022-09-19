package com.example.freshcart.infrastructure.config;

import com.example.freshcart.domain.User;
import com.example.freshcart.domain.UserRepository;
import com.example.freshcart.infrastructure.UserMapper;
import java.util.List;
import java.util.Optional;

/*
MyBatis에서 Mapper는 인터페이스여야 하기 때문에, UserRepository를 구현할 수 없음
중간에 Adaptor를 사용해서 UserRepository를 구현하면서, 내부에서는 UserMapper의 메서드에 접근하도록 함
 */

public class UserMapperRepositoryAdaptor implements UserRepository {

  private final UserMapper userMapper;

  public UserMapperRepositoryAdaptor(UserMapper userMapper) {
    this.userMapper = userMapper;
  }

  @Override
  public void save(User user) {
    userMapper.save(user);
  }

  @Override
  public User findEmailDuplicate(String email) {
    return userMapper.findEmailDuplicate(email);
  }

  @Override
  public Optional<User> findByUserEmail(String email) {
    return userMapper.findByUserEmail(email);
  }

  @Override
  public List<User> findAll() {
    return userMapper.findAll();
  }

}
