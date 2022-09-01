package com.example.freshCart.application;

import com.example.freshCart.application.command.LoginCommand;
import com.example.freshCart.application.command.SignupCommand;
import com.example.freshCart.domain.PasswordEncoder;
import com.example.freshCart.domain.User;
import com.example.freshCart.domain.UserRepository;
import com.example.freshCart.infrastructure.exception.PasswordDoesNotMatchException;
import com.example.freshCart.infrastructure.exception.UserNotExistsException;

public class UserRegisterService {

    private UserRepository userRepository;
    private PasswordEncoder passwordEncoder;

    public UserRegisterService(UserRepository userRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    public void register(SignupCommand request) {
        userRepository.findByEmail(request.from(request));
        //가입 시에 비밀번호를 암호화 시켜준다.
        String password = request.getPassword();
        request.toEncryptPassword(passwordEncoder.encrypt(password));
        userRepository.save(request);
    }

    //Login 아이디가 일치하는 값을 먼저 찾아오고 password 비교해서 가져오기. 일치하지 않을 경우 exception 반환.
    public LoginUser signIn(LoginCommand request) {
        User emailMatched = userRepository.findByLoginId(request.getEmail()).orElse(null);
        LoginUser user = LoginUser.of(emailMatched.getEmail(), emailMatched.getPassword());
        if(emailMatched == null) {
            throw new UserNotExistsException("존재하지 않는 유저입니다");
        }
        Boolean passwordMatch = passwordEncoder.isMatch(request.getPassword(), user.getPassword());
        if(!passwordMatch) {
            throw new PasswordDoesNotMatchException("비밀번호가 일치하지 않습니다");
        }
        return user;

    }
}
