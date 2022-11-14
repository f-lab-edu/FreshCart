package com.example.freshcart.user.presentation.request;

import com.example.freshcart.user.application.command.LoginCommand;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 사용자가 로그인 시도 시 정보를 담아서 요청.
 */
public class LoginRequest {

  @NotBlank(message = "이메일을 입력해주세요")
  @Email(message = "올바른 형식의 이메일 주소어야 합니다")
  private String email;

  @NotBlank(message = "패스워드를 입력해주세요")
  private String password;

  public LoginRequest(String email, String password) {
    this.email = email;
    this.password = password;
  }

  public LoginRequest() {
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public LoginCommand toCommand(){
    return new LoginCommand(this.email, this.password);
  }
}