package com.example.freshcart.authentication.application;

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

  void expireSession(HttpServletRequest request);
}
