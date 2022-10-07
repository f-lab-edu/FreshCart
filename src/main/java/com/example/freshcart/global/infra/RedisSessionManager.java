package com.example.freshcart.global.infra;

import com.example.freshcart.global.domain.SessionManager;
import com.example.freshcart.user.application.LoginUser;
import java.util.Arrays;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class RedisSessionManager implements SessionManager {

  public static final String SESSION_COOKIE_NAME = "MySessionId";
  private SessionRedisRepository redisRepository;

  public RedisSessionManager(
      SessionRedisRepository redisRepository) {
    this.redisRepository = redisRepository;
  }


  public void createSession(LoginUser loginUser, HttpServletResponse response) {

    Cookie cookie = new Cookie(SESSION_COOKIE_NAME, loginUser.getSessionId());
    cookie.setMaxAge(2 * 60 * 60); //2시간.
    cookie.setPath("/");
    response.addCookie(cookie);
    redisRepository.save(loginUser);
    log.info("@ID가 자동으로 생성한 ID 확인:" + loginUser.getId());

    log.info("redis session manager 동작 중입니다");
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
        .filter(c -> (c.getName().equals(cookieName)) && (c.getMaxAge() != 0))
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
    log.info("sessionCookie 값 입니다" + sessionCookie.getValue());

    return redisRepository.findBySessionId(sessionCookie.getValue()).get();
  }

  /*
  세션 만료
  클라이언트가 요청한 sessionId 쿠키의 값으로, 세션 저장소에 보관한 sessionId와 값 제거
  Session을 꺼내올 때, 쿠키를 key로 가져오기 때문에, 세션을 delete 할 필요 없이 (어차피 TTL 이 정해져 있다)
  쿠키를 지우자 (MaxAge를 0으로 해서 더하면 된다)
   */

  public void expireSession(HttpServletResponse response) {
    Cookie cookie = new Cookie(SESSION_COOKIE_NAME, null);
    cookie.setMaxAge(0);
    cookie.setPath("/");
    response.addCookie(cookie);
    log.info("세션이 만료되었습니다");
  }
}