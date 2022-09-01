package com.example.freshCart.application.command;

public class SignupCommand {
    private String email;
    private String password;
    private String name;

  public SignupCommand(String email, String password, String name) {
        this.email = email;
        this.password = password;
        this.name = name;
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

  public static EmailDupCheckCommand from(SignupCommand request) {
    return new EmailDupCheckCommand(request.getEmail());
    }

  // 암호화된 패스워드로 바꾸기 BCrytpEncoder에서 가져와야 함.
  public void toEncryptPassword(String encryptedPassword) {
        this.password = encryptedPassword;
    }
}
