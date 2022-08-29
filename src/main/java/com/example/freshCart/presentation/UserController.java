package com.example.freshCart.presentation;

import com.example.freshCart.application.UserRegisterServiceV1;
import com.example.freshCart.domain.AuthenticationService;
import com.example.freshCart.domain.User;
import com.example.freshCart.domain.UserRepository;
import com.example.freshCart.infrastructure.exception.InvalidLoginTryException;
import com.example.freshCart.presentation.request.EmailDupCheckRequest;
import com.example.freshCart.presentation.request.LoginRequest;
import com.example.freshCart.presentation.request.SignupRequest;
import com.example.freshCart.util.SessionUtil;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/*
회원가입의 세부 로직이 바뀔 수 있음을 고려해 인터페이스에 의존하게 설계하자.
 */
@RestController
@RequestMapping("/users")
public class UserController {

    private UserRepository userRepository;
    private UserRegisterServiceV1 userRegisterService;
    private AuthenticationService authentication;

    public UserController(UserRepository userRepository,
            UserRegisterServiceV1 userRegisterService) {
        this.userRepository = userRepository;
        this.userRegisterService = userRegisterService;
    }

    /*
    회원 가입
     */
    @PostMapping("/signup")
    public void Signup(@RequestBody SignupRequest request){
        userRegisterService.toRegister(request);
    }

    /*
    exception 처리 필요.
     */

    @GetMapping("/login")
    public void Login(@RequestBody LoginRequest request, HttpServletRequest httpRequest) {
       User loginUser = userRegisterService.toSignIn(request);
        if(loginUser == null) {
            throw new InvalidLoginTryException( "아이디 또는 비밀번호가 일치하지 않습니다");
      }
        HttpSession session = httpRequest.getSession();
        session.setAttribute(SessionUtil.LOGIN_USER, loginUser);
    }
}