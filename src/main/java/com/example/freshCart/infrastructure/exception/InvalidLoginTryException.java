package com.example.freshCart.infrastructure.exception;

public class InvalidLoginTryException extends RuntimeException {

    private String message = "아이디 또는 비밀번호가 일치하지 않습니다";
    public InvalidLoginTryException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return message;
    }

}
