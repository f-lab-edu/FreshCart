package com.example.freshCart.infrastructure;

import com.example.freshCart.domain.UserRepository;
import com.example.freshCart.domain.User;
import com.example.freshCart.presentation.request.EmailDupCheckRequest;
import com.example.freshCart.presentation.request.SignupRequest;
import java.util.Map;
import java.util.Optional;
import java.util.concurrent.ConcurrentHashMap;
import java.util.*;

/*
store에서 User를 찾을 때, 고유값/중복 체크가 가능한 이메일 주소로 Key설정.
 */

public class UserInMemoryRepository implements UserRepository {

    private static Map<String, User> store = new ConcurrentHashMap<>();
    private static Long sequence = 0L;

    @Override
    public User save(SignupRequest request) {
        User user = new User(request.getEmail(), request.getPassword(), request.getName());
        user.setId(++sequence);
        store.put(user.getEmail(), user);
        return user;
    }

    @Override
    public Optional<User> findByLoginId(String email) {
        return findAll().stream().filter(u -> u.getEmail().equals(email)).findFirst();
    }

    @Override
    public List<User> findAll() {
        return new ArrayList<>(store.values());
    }

    @Override
    public boolean findByEmail(EmailDupCheckRequest request) {
        User user = store.getOrDefault(request.getEmail(), null);
        if(user == null) {
            return false;
        }
        else {
            return true;
        }
    }
}
