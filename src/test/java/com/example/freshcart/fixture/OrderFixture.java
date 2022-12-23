package com.example.freshcart.fixture;

import com.example.freshcart.order.presentation.request.Cart;
import com.example.freshcart.order.presentation.request.Cart.CartItem;
import com.example.freshcart.order.presentation.request.Cart.CartItemOption;
import com.example.freshcart.order.presentation.request.Cart.CartItemOptionGroup;
import java.util.Arrays;

/**
 *
 */
public class OrderFixture {
//  public static Cart.CartBuilder aCart() {
//    return Cart.builder()
//        .receiverName("유저1")
//        .receiverPhone("01000000000")
//        .receiverAddress("서울시 강남구 강남대로 22 교보빌딩 1층")
//        .cartItems(Arrays.asList(aCartItems().build(), aCartItems().));
//  }

  public static CartItem.CartItemBuilder aCartItems(){
    return CartItem.builder()
        .productId(1L)
        .name("샐러드")
        .price(2000)
        .count(1)
        .groups(Arrays.asList(aCartItemOptionGroup().
            build(),
            aCartItemOptionGroup()
                .optionGroupId(2L)
                .name("토핑")
                .options(Arrays.asList(
                    aCartItemOptions().optionId(3L).name("견과류").price(1000).build(),
                    aCartItemOptions().optionId(4L).name("크랜베리").price(500).build()))
                .build()));
  }

  public static CartItemOptionGroup.CartItemOptionGroupBuilder aCartItemOptionGroup(){
    return CartItemOptionGroup.builder()
        .optionGroupId(1L)
        .name("중량")
        .options(Arrays.asList(aCartItemOptions().build()));
  }

  public static CartItemOption.CartItemOptionBuilder aCartItemOptions(){
    return CartItemOption.builder()
        .optionId(1L)
        .name("30g")
        .price(0);
  }
}
