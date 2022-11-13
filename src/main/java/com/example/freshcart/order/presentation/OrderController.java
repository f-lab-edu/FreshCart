package com.example.freshcart.order.presentation;

import com.example.freshcart.authentication.annotation.AuthenticatedUser;
import com.example.freshcart.authentication.annotation.Authentication;
import com.example.freshcart.authentication.annotation.LoginCheck;
import com.example.freshcart.authentication.Role;
import com.example.freshcart.authentication.application.LoginUser;
import com.example.freshcart.order.application.OrderManagerFacade;
import com.example.freshcart.order.presentation.request.Cart;
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
    orderManagerFacade.register(user, cart.toCommand());
    log.info(user.getEmail() + "로그인된 유저임을 확인");
  }
}
