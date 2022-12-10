package com.example.freshcart.global.utils;

import static com.example.freshcart.authentication.Role.SELLER;

import com.example.freshcart.authentication.application.LoginUser;
import com.example.freshcart.global.exception.UnauthorizedRequestException;

/**
 * 판매자로 수정 권한을 얻으려면, ROLE이 SELLER 이고, user 의 userId와 Option의 SELLER ID 가 일치해야 한다.
 * 이에 해당하지 않을 경우 Exception 터짐.
 */
public class VerifyUserUtils {

  public static void verifyOwner(LoginUser user, Long sellerId){
    if(!(user.getRole()==SELLER && user.getUserId()==sellerId)) {
      throw new UnauthorizedRequestException();
    }
  }
}
