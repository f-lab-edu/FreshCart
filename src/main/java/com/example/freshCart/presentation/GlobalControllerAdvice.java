package com.example.freshcart.presentation;

import com.example.freshcart.domain.ErrorResult;
import com.example.freshcart.infrastructure.exception.BaseException;
import com.example.freshcart.infrastructure.exception.UnExpectedException;
import javax.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@Slf4j
@RestControllerAdvice
public class GlobalControllerAdvice {
  /*
  애플리케이션에서 만든 에러(baseException)처리
   */

  @ExceptionHandler(BaseException.class)
  public ResponseEntity<ErrorResult> baseExceptionHandler(
      BaseException e, HttpServletRequest request) {
    return createErrorResult(e, request);
  }

  /*
   애플리케이션에서 지정하지 않은 (예상치 못한) 에러(baseException)처리
   BaseException 으로 바로 cast할 수 없기 때문에,exception 에 감싸서 넣어준다.
  */
  @ExceptionHandler(Exception.class)
  public ResponseEntity<ErrorResult> unExpectedExceptionHandler(
      Exception e, HttpServletRequest request) {
    UnExpectedException exception = new UnExpectedException(e);
    return createErrorResult(exception, request);
  }

  /*
  Body와 statusCode로 이루어진 ResponseEntity 객체 반환.
   */
  public ResponseEntity<ErrorResult> createErrorResult(
      BaseException e, HttpServletRequest request) {
    return ResponseEntity.status(e.getStatus())
        .body(
            ErrorResult.builder()
                .status(e.getStatus().value())
                .error(e.getStatus().name())
                .message(e.getMessage())
                .path(request.getRequestURI())
                .build());
  }
}
