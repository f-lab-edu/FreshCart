package com.example.freshcart.user.presentation.request;

import com.example.freshcart.authentication.Role;
import com.example.freshcart.user.application.command.SignupCommand;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

/**
 사용자가 필수 정보를 담아서 회원 가입 요청. 필수이기 때문에 @NotNull과 @Valid로 확인
 */
public class SignupRequest {
  @NotBlank(message = "이메일을 입력해주세요")
  @Email(message = "올바른 형식의 이메일 주소어야 합니다")
  private String email;

  @NotBlank(message = "패스워드를 입력해주세요")
  private String password;

  @NotBlank(message = "전화번호를 입력해주세요")
  private String phoneNumber;

  @NotBlank(message = "이름을 입력해주세요")
  private String name;

  @NotNull
  private Role role;

  public SignupRequest(String email, String password, String phoneNumber, String name,
      Role role) {
    this.email = email;
    this.password = password;
    this.phoneNumber = phoneNumber;
    this.name = name;
    this.role = role;
  }

  public String getEmail() {
    return email;
  }

  public String getPassword() {
    return password;
  }

  public String getName() {
    return name;
  }

  public Role getRole() {
    return role;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public SignupCommand toCommand(){
    return new SignupCommand(this.email, this.password,this.phoneNumber,this.name,this.role);
  }
}
