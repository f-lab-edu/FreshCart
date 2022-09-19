package com.example.freshcart.product.presentation;


import com.example.freshcart.product.application.ProductRegisterCommand;
import com.example.freshcart.product.application.ProductService;
import com.example.freshcart.product.presentation.argumentresolver.SellerCheck;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/products")
public class ProductController {

  private ProductService productService;

  @SellerCheck
  @PostMapping("/register")
  public void register(@Valid @RequestBody ProductRegisterCommand request){
      productService.register(request);
  }

}
