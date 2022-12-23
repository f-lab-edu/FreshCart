package com.example.freshcart.product.application;

import static com.example.freshcart.product.fixture.ProductFixture.aProductRegisterRequest;

import com.example.freshcart.product.presentation.request.ProductRegisterRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class ProductServiceTest {

  @DisplayName("판매자가 아닌 유저가 숙소 등록을 시도할 경우 NotSeller예외가 발생한다")
  @Test
  public void createProduct_fail(){
    //given 일반 유저가
    aProductRegisterRequest();
  }

  @DisplayName("판매자인 유저가 숙소 등록을 시도할 경우 정상")
  @Test
  public void createProduct_success(){
    //given 일반 유저가
    aProductRegisterRequest();
  }

  @DisplayName("옵션이 있는 제품 저장에 성공 시 id 가 생성된다 ")
  @Test
  void createProductwithOptions(){

  }


}
