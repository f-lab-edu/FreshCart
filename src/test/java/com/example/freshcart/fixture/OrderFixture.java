package com.example.freshcart.fixture;

import com.example.freshcart.order.presentation.request.Cart;
import com.example.freshcart.order.presentation.request.Cart.CartItem;
import com.example.freshcart.order.presentation.request.Cart.CartItemOption;
import java.util.Arrays;

/**
 *
 */
public class OrderFixture {
  //여러 제품 등록 필요. 현재는 한 item만 있음.
  public static Cart.CartBuilder aCart() {
    return Cart.builder()
        .receiverName("유저1")
        .receiverPhone("01000000000")
        .receiverAddress("서울시 강남구 강남대로 22 교보빌딩 1층")
        .cartItems(Arrays.asList(aCartItems().build()));
  }

  public static CartItem.CartItemBuilder aCartItems(){
    return CartItem.builder()
        .productId(1L)
        .count(1)
        .groups(Arrays.asList(
            aCartItemOptions().optionId(3L).build(),
                    aCartItemOptions().optionId(4L).build()));
  }


  public static CartItemOption.CartItemOptionBuilder aCartItemOptions(){
    return CartItemOption.builder()
        .optionId(1L);
  }
}
