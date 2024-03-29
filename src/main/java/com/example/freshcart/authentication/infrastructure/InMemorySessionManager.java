package com.example.freshcart.authentication.infrastructure;

import com.example.freshcart.authentication.application.LoginUser;
import com.example.freshcart.authentication.application.SessionManager;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

/**
 * HttpSession 객체를 직접 구현 초기 버전 - Redis 도입 전 HashMap으로 세션 저장소 구현
 */

@Slf4j
public class InMemorySessionManager implements SessionManager {

  public static final String SESSION_COOKIE_NAME = "MySessionId";
  private Map<String, LoginUser> sessionStore = new ConcurrentHashMap<>();

  /*
  (1) 세션 저장소에 세션 아이디/로그인 객체를 저장.
  (2) 쿠키 생성. Response 할 때, 쿠키를 담아서 보냄.
   */

  public void createSession(LoginUser loginUser, HttpServletResponse response) {
    String sessionId = UUID.randomUUID().toString();
    loginUser.setSessionId(sessionId);
    sessionStore.put(sessionId, loginUser);
    Cookie cookie = new Cookie(SESSION_COOKIE_NAME, sessionId);
    cookie.setPath("/");
    response.addCookie(cookie);
  }

  /*
  (1) 쿠키가 아예 없다면 null 처리를 하고,
  (2) request 의 쿠키가 Session 저장소에 저장되었는지 조회하고 반환.
  (Session Id를 키로 일치하는 지 확인 가능)
  */
  private Cookie findCookie(HttpServletRequest request, String cookieName) {
    final Cookie[] cookies = request.getCookies();
    if (cookies == null) {
      return null;
    }
    return Arrays.stream(cookies)
        .filter(c -> c.getName().equals(cookieName))
        .findFirst()
        .get();
  }

  /*
  세션 조회.
  (1) findCookie의 조회 결과가 없다면 null 처리를 하고,
  (2) 일치할 경우 cookie 의 값과(sessionId) 일치하는 세션을 꺼내온다.
  */
  public LoginUser getSession(HttpServletRequest request) {
    Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
    if (sessionCookie == null) {
      return null;
    }
    return sessionStore.get(sessionCookie.getValue());
  }


  public void expireSession(HttpServletRequest request) {
    Cookie sessionCookie = findCookie(request, SESSION_COOKIE_NAME);
    if (sessionCookie != null) {
      sessionStore.remove(sessionCookie.getValue());
    }
  }
}