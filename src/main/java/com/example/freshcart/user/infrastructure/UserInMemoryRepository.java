package com.example.freshcart.user.infrastructure;

import com.example.freshcart.user.domain.UserRepository;
import com.example.freshcart.user.domain.User;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;

/**
 * store에서 User를 찾을 때, 고유값/중복 체크가 가능한 이메일 주소로 Key 설정.
 */

public class UserInMemoryRepository implements UserRepository {

  private static Map<String, User> store = new ConcurrentHashMap<>();
  private static Long sequence = 0L;

  @Override
  public User save(User user) {
    user.setId(++sequence);
    store.put(user.getEmail(), user);
    return store.get(user.getEmail());
  }

  /**
   * LoginId 와 일치하는 데이터가 있으면 꺼내올 것. FindFirst 를 붙여야, stream의 요소 중 조건에 맞는 첫 번째 결과를 반환함.
   */
  @Override
  public User findByUserEmail(String email) {
    Optional<User> user = findAll().stream().filter(u -> u.getEmail().equals(email)).findFirst();
    if (user.isPresent()) {
      return user.get();
    } else {
      return null;
    }
  }

  /**
   * Collections를 리턴하기 때문에 ArrayList(Collections c)로 value를 직접 입력. 검색을 위해 User 객체 전부 (store의 값 전체)를
   * ArrayList에 넣는다.
   */

  @Override
  public List<User> findAll() {
    return new ArrayList<>(store.values());
  }

}
