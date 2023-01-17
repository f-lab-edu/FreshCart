
//package com.example.freshcart.product.application;
//
//import static org.junit.jupiter.api.Assertions.assertThrows;
//
//import com.example.freshcart.authentication.application.LoginUser;
//import com.example.freshcart.fixture.UserFixture;
//import com.example.freshcart.product.domain.exception.NotSellerException;
//import com.example.freshcart.product.fixture.ProductFixture;
//import com.example.freshcart.user.application.UserService;
//import com.example.freshcart.user.application.command.SignupCommand;
//import com.example.freshcart.user.domain.User;
//import java.time.LocalDateTime;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.transaction.annotation.Transactional;
//

//프로덕트 서비스 코드 작성 중
//@SpringBootTest
//@Transactional
//public class ProductServiceV1Test {
//
//  @Autowired
//  private ProductService productService;
//  @Autowired
//  private UserService userServiceV2;
//
//  private SignupCommand signupCommand;
//  private SignupCommand aSellerSignupCommand;
//
//  private LoginUser loginSeller;
//  private LoginUser loginUser;
//
//  @BeforeEach
//  void init() {
//    //유저가 회원가입.
//    signupCommand = UserFixture.aSignupCommand().build();
//    aSellerSignupCommand = UserFixture.aSellerSignupCommand().build();;
//
//    User user = userServiceV2.register(signupCommand);
//    User seller =  userServiceV2.register(aSellerSignupCommand);
//
//    //판매자
//    loginSeller =  new LoginUser("sellerSessionId", seller.getId(), seller.getEmail(), seller.getRole(),  LocalDateTime.now());
//    loginUser = new LoginUser("sellerSessionId", user.getId(), user.getEmail(), user.getRole(), LocalDateTime.now());
//
//    //일반 유저가 로그인.
//  }
//
//  @DisplayName("판매자가 아닌 유저가 숙소 등록을 시도할 경우 NotSeller예외가 발생한다")
//  @Test
//  public void createProduct_fail() {
//    //given 로그인 유저가 Role이 Seller가 아님.
//    //when
//    assertThrows(NotSellerException.class,
//        () ->     productService.register(loginUser, ProductFixture.aProductRegisterRequest().build()));
//  }
//
//  @DisplayName("판매자인 유저가 숙소 등록을 시도할 경우 정상")
//  @Test
//  public void createProduct_success() {
//    //given 로그인 유저가 Role이 Seller가 아님.
//    //when
//    productService.register(loginSeller, ProductFixture.aProductRegisterRequest().build());
//
//  }
//    @DisplayName("일반 사용자가 숙소 등록 시 예외가 발생한다.")
//    @Test
//
//    @DisplayName("OptionGroup이나 Option 등의 저장이 실패할 경우 Product도 저장되지 않는다")
//    @Test
//
////  @DisplayName("옵션이 있는 제품 저장에 성공 시 id 가 생성된다 ")
////  @Test
////  void createProductwithOptions(){
////
////  }
//
//    @DisplayName("존재하지 않는 카테고리 저장을 시도할 경우, 저장할 수 없다")
//  }
