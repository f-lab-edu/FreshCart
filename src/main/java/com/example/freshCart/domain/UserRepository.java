package com.example.freshCart.domain;

import com.example.freshCart.presentation.request.EmailDupCheckRequest;
import com.example.freshCart.presentation.request.SignupRequest;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(SignupRequest request);
    boolean findByEmail(EmailDupCheckRequest request);
    Optional<User> findByLoginId(String email);
    List<User> findAll();
}
