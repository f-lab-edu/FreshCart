package com.example.freshCart.presentation;

import com.example.freshCart.application.LoginUser;
import java.util.Arrays;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import lombok.extern.slf4j.XSlf4j;
import org.springframework.stereotype.Component;

/*
1. 쿠키에 SessionId (랜덤을 저장한다)를 저장한다.
2.
 */
@Component
@Slf4j
public class SessionManager {

  public static final String Session_Cookie_Name = "MySessionId";
  private Map<String, LoginUser> sessionStore = new ConcurrentHashMap<>();

  /*
  (1) 세션 저장소에 세션 아이디/로그인 객체를 저장.
  (2) 쿠키 생성. Response 할 때, 쿠키를 담아서 보냄.
   */

  public void createSession(LoginUser check, HttpServletResponse response) {
    String sessionId = UUID.randomUUID().toString();
    sessionStore.put(sessionId, check);
    Cookie cookie = new Cookie(Session_Cookie_Name, sessionId);
    response.addCookie(cookie);
  }

  /*
  (1) 쿠키가 아예 없다면 null 처리를 하고,
  (2) request 의 쿠키가 Session 저장소에 저장되었는지 조회
  (Session Id를 키로 일치하는 지 확인 가능)

  **get(): value가 null일 경우 no such element 반환
  */
  private Cookie findCookie(HttpServletRequest request, String Session_Cookie_Name) {
    final Cookie[] cookies = request.getCookies();
    if (cookies == null) {
      return null;
    }
    return Arrays.stream(cookies)
        .filter(c -> c.getName().equals(Session_Cookie_Name))
        .findFirst()
        .get();
  }
  /*
  세션 조회.
  request 의 쿠키가 Session 저장소의 Session Id와 일치하는 지.
  (1) 쿠키가 아예 없다면 null 처리를 하고,
  (2) 일치할 경우 cookie 의 값과 일치하는 세션을 꺼내온다.
  */
  public LoginUser getSession(HttpServletRequest request) {
    Cookie sessionCookie = findCookie(request, Session_Cookie_Name);
    if (sessionCookie == null) {
      return null;
    }
    return sessionStore.get(sessionCookie.getValue());
  }

  /*
  세션 만료
  클라이언트가 요청한 sessionId 쿠키의 값으로, 세션 저장소에 보관한 sessionId와 값 제거
  여러번 가능하다(?)
   */

  public void expireSession(HttpServletRequest request) {
    Cookie sessionCookie = findCookie(request, Session_Cookie_Name);
    if (sessionCookie != null) {
      sessionStore.remove(sessionCookie.getValue());
      log.info("세션이 만료되었습니다");
    }
  }
}
