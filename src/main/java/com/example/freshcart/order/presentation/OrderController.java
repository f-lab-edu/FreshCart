package com.example.freshcart.order.presentation;

import com.example.freshcart.global.argumentresolver.AuthenticatedUser;
import com.example.freshcart.global.argumentresolver.Authentication;
import com.example.freshcart.global.argumentresolver.LoginCheck;
import com.example.freshcart.global.domain.Role;
import com.example.freshcart.order.application.OrderManagerFacade;
import com.example.freshcart.order.presentation.request.Cart;
import com.example.freshcart.user.application.LoginUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/orders")
public class OrderController {

  private final OrderManagerFacade orderManagerFacade;

  public OrderController(
      OrderManagerFacade orderManagerFacade) {
    this.orderManagerFacade = orderManagerFacade;
  }

  @Authentication(authority = Role.SELLER)
  @LoginCheck
  @PostMapping("/register")
  public void register(@RequestBody Cart cart, @AuthenticatedUser LoginUser user) {
    orderManagerFacade.register(user, cart);
    log.info(user.getEmail() + "로그인된 유저임을 확인");
  }
}
