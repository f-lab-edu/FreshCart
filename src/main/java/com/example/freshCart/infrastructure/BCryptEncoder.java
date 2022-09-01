package com.example.freshCart.infrastructure;

import com.example.freshCart.domain.PasswordEncoder;
import org.mindrot.jbcrypt.BCrypt;

public class BCryptEncoder implements PasswordEncoder {

    public BCryptEncoder() {
    }

    @Override
    public String encrypt(String password) {
        String hashPassword = BCrypt.hashpw(password, BCrypt.gensalt());
        return hashPassword;
    }

    @Override
    public Boolean isMatch(String candidatePassword, String storedPassword) {
        Boolean result = BCrypt.checkpw(candidatePassword, storedPassword);
        return result;
    }
}
