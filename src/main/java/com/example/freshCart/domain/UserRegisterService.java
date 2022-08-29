package com.example.freshCart.domain;

import com.example.freshCart.presentation.request.EmailDupCheckRequest;
import com.example.freshCart.presentation.request.LoginRequest;
import com.example.freshCart.presentation.request.SignupRequest;

public interface UserRegisterService {

    void toRegister(SignupRequest request);

    User toSignIn(LoginRequest request);
}
