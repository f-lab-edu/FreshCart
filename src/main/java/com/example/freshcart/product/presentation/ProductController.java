package com.example.freshcart.product.presentation;

import com.example.freshcart.authentication.application.LoginUser;
import com.example.freshcart.product.application.ProductServiceV1;
import com.example.freshcart.product.presentation.request.ProductRegisterRequest;
import com.example.freshcart.authentication.Role;
import com.example.freshcart.authentication.annotation.AuthenticatedUser;
import com.example.freshcart.authentication.annotation.LoginCheck;
import com.example.freshcart.authentication.annotation.Authentication;
import javax.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping("/products")
public class ProductController {

  private ProductServiceV1 productService;

  public ProductController(ProductServiceV1 productService) {
    this.productService = productService;
  }

  @Authentication(authority = Role.SELLER)
  @LoginCheck
  @PostMapping("/register")
  public void register(@AuthenticatedUser LoginUser user,
      @RequestBody @Valid ProductRegisterRequest request) {
    productService.register(user, request);
    log.info(user.getEmail() + "로그인된 유저임을 확인");
  }

}
