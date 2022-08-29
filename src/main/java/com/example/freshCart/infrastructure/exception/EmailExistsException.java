package com.example.freshCart.infrastructure.exception;

public class EmailExistsException extends RuntimeException {

    private String message = "이미 존재하는 이메일입니다";
    public EmailExistsException(String message) {
        super(message);
    }

    @Override
    public String getMessage() {
        return message;
    }
}
