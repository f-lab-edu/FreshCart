package com.example.freshCart.domain;


import com.example.freshCart.application.command.EmailDupCheckCommand;
import com.example.freshCart.application.command.SignupCommand;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    User save(SignupCommand request);
    void findByEmail(EmailDupCheckCommand request);
    Optional<User> findByLoginId(String email);
    List<User> findAll();
}
