package com.example.freshcart.fixture;

import static com.example.freshcart.authentication.Role.SELLER;
import static com.example.freshcart.authentication.Role.USER;

import com.example.freshcart.user.application.command.LoginCommand;
import com.example.freshcart.user.application.command.SignupCommand;
import com.example.freshcart.user.domain.User;
import com.example.freshcart.user.presentation.request.SignupRequest;

/**
 * 판매자 유저, 일반 유저 생성.
 * UserService에서 password를 encrypt를 한다.
 * UserRequestDto를 먼저 만든다.
 */
public class UserFixture {
  public static User.UserBuilder aUser(){
    return User.builder().email("user1@gmail.com").password("tgbv1245").phoneNumber("01033334444").name("유저1").role(USER);
  }

  public static User.UserBuilder aSeller(){
    return User.builder().email("seller1@gmail.com").password("lopp1245").phoneNumber("01011111111").name("판매자1").role(SELLER);
  }

  //가입에만 사용하는 데이터
  //유저 회원 가입 신청
  public static SignupCommand.SignupCommandBuilder aSignupCommand(){
    return SignupCommand.builder()
        .email("user1@gmail.com")
        .password("tgbv1245")
        .phoneNumber("01033334444")
        .name("유저1")
        .role(USER);
  }

  //판매자 회원 가입 신청
  public static SignupCommand.SignupCommandBuilder aSellerSignupRequest(){
    return SignupCommand.builder()
        .email("seller1@gmail.com")
        .password("lopp1245")
        .phoneNumber("01011111111")
        .name("판매자1")
        .role(SELLER);
  }

  public static LoginCommand.LoginCommandBuilder aLoginCommand(){
    return LoginCommand.builder()
        .email("seller1@gmail.com")
        .password("lopp1245");
  }
}
