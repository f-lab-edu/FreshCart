package com.example.freshcart.global.infra;

import com.example.freshcart.global.domain.SessionManager;
import com.example.freshcart.user.application.LoginUser;
import java.util.Arrays;
import java.util.UUID;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * RedisTemplate을 변수로 활용한 SessionManager 구현체
 */

@Slf4j
public class RedisSessionTemplateManager implements SessionManager {

  public static final String SESSION_COOKIE_NAME = "MySessionId";
  private SessionRedisTemplate sessionRedisTemplate;

  public RedisSessionTemplateManager(
      SessionRedisTemplate sessionRedisTemplate) {
    this.sessionRedisTemplate = sessionRedisTemplate;
  }

  @Override
  public void createSession(LoginUser loginUser, HttpServletResponse response) {

    String sessionId = UUID.randomUUID().toString();
    loginUser.setSessionId(sessionId);
    sessionRedisTemplate.save(loginUser);
    log.info("redisSessionManager 동작 중 - @ID가 자동으로 생성한 ID 확인:" + loginUser.getId());
    Cookie cookie = new Cookie(SESSION_COOKIE_NAME, loginUser.getSessionId());
    cookie.setMaxAge(2 * 60 * 60); //2시간.
    cookie.setPath("/");
    response.addCookie(cookie);
  }

  private Cookie findCookie(HttpServletRequest request, String cookieName) {
    final Cookie[] cookies = request.getCookies();
    if (cookies == null) {
      return null;
    }
    return Arrays.stream(cookies)
        .filter(c -> (c.getName().equals(cookieName)) && (c.getMaxAge() != 0))
        .findFirst()
        .get();
  }

  @Override
  public LoginUser getSession(HttpServletRequest request) {
    Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
    if (sessionCookie == null) {
      return null;
    }
    log.info("sessionCookie 값 입니다" + sessionCookie.getValue());
    log.info(
        "redisTemplate에서 찾아온 값" + sessionRedisTemplate.findBySessionId(sessionCookie.getValue()));

    return sessionRedisTemplate.findBySessionId(sessionCookie.getValue());
  }

  @Override
  public void expireSession(HttpServletResponse response) {
    Cookie cookie = new Cookie(SESSION_COOKIE_NAME, null);
    cookie.setMaxAge(0);
    cookie.setPath("/");
    response.addCookie(cookie);
    log.info("세션이 만료되었습니다");
  }
}

