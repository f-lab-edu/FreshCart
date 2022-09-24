package com.example.freshcart.product.presentation;


import com.example.freshcart.product.application.command.ProductRegisterCommand;
import com.example.freshcart.product.application.ProductService;
import com.example.freshcart.user.application.LoginUser;
import com.example.freshcart.user.presentation.web.argumentresolver.AuthenticatedUser;
import com.example.freshcart.user.domain.LoginCheck;
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

  private ProductService productService;

  public ProductController(ProductService productService) {
    this.productService = productService;
  }

  @LoginCheck
  @PostMapping("/register")
  public void register(@AuthenticatedUser LoginUser user, @RequestBody @Valid ProductRegisterCommand request){
    productService.register(user, request);
    log.info(user.getEmail() + "로그인된 유저임을 확인");
  }
}
