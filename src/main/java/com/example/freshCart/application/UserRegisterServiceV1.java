package com.example.freshCart.application;

import com.example.freshCart.domain.PasswordEncoder;
import com.example.freshCart.domain.User;
import com.example.freshCart.domain.UserRegisterService;
import com.example.freshCart.domain.UserRepository;
import com.example.freshCart.presentation.request.EmailDupCheckRequest;
import com.example.freshCart.presentation.request.LoginRequest;
import com.example.freshCart.presentation.request.SignupRequest;
import com.example.freshCart.infrastructure.exception.EmailExistsException;
import java.util.Optional;

/*
회원 가입: 이메일 중복 확인. 중복일 경우 EmailAlreadyExists Error
역할: 회원 가입
회원 가입 절차의 추가/변경을 고려하여 인터페이스로 설계
 */
public class UserRegisterServiceV1 implements UserRegisterService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserRegisterServiceV1(UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    /*
        중복체크를 별도 API 로 처리하더라도, 중복일 경우 가입 자체가 되지 않도록 예외 처리
         */
    @Override
    public void toRegister(SignupRequest request) {
        if(userRepository.findByEmail(SignupRequest.from(request))) {
            throw new EmailExistsException("이미 존재하는 이메일입니다");
        }
        //가입 시에 비밀번호를 암호화 시켜준다.
        String password = request.getPassword();
        request.toEncryptPassword(passwordEncoder.encrypt(password));
        userRepository.save(request);
    }

    //Login 아이디가 일치하는 값을 먼저 찾아오고 password 비교해서 가져오기. 일치하지 않을 경우 exception 반환.
    public User toSignIn(LoginRequest request) {
        return userRepository.findByLoginId(request.getEmail()).filter(u -> u.getPassword().equals(request.getPassword())).orElse(null);
    }
}
