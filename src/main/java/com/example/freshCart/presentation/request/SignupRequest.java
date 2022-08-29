package com.example.freshCart.presentation.request;


public class SignupRequest {
    private String email;
    private String password;
    private String name;

    public SignupRequest(String email, String password, String name) {
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


    public static EmailDupCheckRequest from(SignupRequest request){
        return new EmailDupCheckRequest(request.getEmail());
    }

    //암호화된 패스워드로 바꾸기
    public void toEncryptPassword(String encryptedPassword) {
        this.password = encryptedPassword;
    }
}
