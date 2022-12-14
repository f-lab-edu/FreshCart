package com.example.freshcart.user.infrastructure;

import com.example.freshcart.user.domain.User;
import com.example.freshcart.user.domain.UserRepository;
import java.util.List;

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
  public User save(User user) {
    userMapper.insert(user);
    return user;
  }

  @Override
  public User findByUserEmail(String email) {
    return userMapper.findByUserEmail(email);
  }

  @Override
  public User findById(Long id) {
    return userMapper.findById(id);
  }

  @Override
  public List<User> findAll() {
    return userMapper.findAll();
  }

}
