package com.example.freshcart.product.application;

import static org.junit.jupiter.api.Assertions.assertThrows;

import com.example.freshcart.authentication.Role;
import com.example.freshcart.authentication.application.LoginUser;
import com.example.freshcart.product.domain.OptionGroupRepository;
import com.example.freshcart.product.domain.OptionRepository;
import com.example.freshcart.product.domain.ProductRepository;
import com.example.freshcart.product.domain.exception.NotSellerException;
import com.example.freshcart.product.presentation.request.ProductRegisterRequest;
import java.time.LocalDateTime;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {
  @InjectMocks
  private ProductServiceV1 productService;
  @Mock
  private ProductRepository productRepository;
  @Mock
  private OptionGroupRepository optionGroupRepository;
  @Mock
  private OptionRepository optionRepository;

  private LoginUser loginUser;
  private ProductRegisterRequest request;

  @BeforeEach
  void init(){
    productService = new ProductServiceV1(productRepository, optionGroupRepository, optionRepository);
  }

  @DisplayName("판매자가 아닌 유저가 숙소 등록을 시도할 경우 NotSeller예외가 발생한다")
  @Test
  public void createProduct_fail(){
    //given 로그인 유저가 Role이 Seller가 아님.
    loginUser = new LoginUser("sessionId", 1L, "user1@gmail.com", Role.USER, LocalDateTime.now());
    assertThrows(NotSellerException.class, () -> productService.register(loginUser, request));
  }

  //ArgumentCaptor 로 객체가 잘 저장되었는지 확인할 것.
//  @DisplayName("판매자인 유저가 숙소 등록을 시도할 경우 정상")
//  @Test
//  public void createProduct_success(){
//    //given
//    loginUser = new LoginUser("sessionId", 1L, "seller1@gmail.com", Role.SELLER, LocalDateTime.now());
//    request = aProductRegisterRequest().build();
//
//    ArgumentCaptor<Product> productCapture= ArgumentCaptor.forClass(Product.class);
//    ArgumentCaptor<OptionGroup> optionGroupCapture= ArgumentCaptor.forClass(OptionGroup.class);
//    ArgumentCaptor<Option> optionCapture= ArgumentCaptor.forClass(Option.class);
//
//    //WHEN
//    productService.register(loginUser, request);
//
//    //THEN
//    verify(productRepository).save(any(Product.class));
//    Product savedProduct = productCapture.getValue();
//    assertThat(savedProduct.getName()).isEqualTo(request.getName());
//
//    //OptionGroup이 여러개인 상황이라면 너무 복잡할 것 같다.
//    verify(optionGroupRepository).save(any(OptionGroup.class));
//    OptionGroup savedOptionGroup = optionGroupCapture.getValue();
////    assertThat(savedOptionGroup.getOptionGroupName()).isEqualTo(request.getOptionSet().getOptionGroupRegister);
//
//    verify(optionRepository).save(any(Option.class));
//    Option savedOption = optionCapture.getValue();
//    assertThat(savedOption.getname()).isEqualTo(request.getOptionSet());
//  }
//
//  @DisplayName("옵션이 있는 제품 저장에 성공 시 id 가 생성된다 ")
//  @Test
//  void createProductwithOptions(){
//
//  }
}
