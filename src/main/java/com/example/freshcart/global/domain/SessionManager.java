package com.example.freshcart.global.domain;

import com.example.freshcart.user.application.LoginUser;
import com.fasterxml.jackson.core.JsonProcessingException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface SessionManager {

  void createSession(LoginUser check, HttpServletResponse response);

  private Cookie findCookie(HttpServletRequest request, String cookieName) {
    return null;
  }

  LoginUser getSession(HttpServletRequest request) throws JsonProcessingException;

  void expireSession(HttpServletResponse response);
}
