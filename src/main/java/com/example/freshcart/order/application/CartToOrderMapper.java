package com.example.freshcart.order.application;

import static java.util.stream.Collectors.toList;

import com.example.freshcart.order.domain.Order;
import com.example.freshcart.order.domain.Order.OrderStatus;
import com.example.freshcart.order.domain.OrderItem;
import com.example.freshcart.order.domain.OrderItemOption;
import com.example.freshcart.order.domain.OrderItemOptionGroup;
import com.example.freshcart.order.presentation.request.Cart;
import com.example.freshcart.user.application.LoginUser;

/**
 * 카트는 데이터를 담아두는 DTO 객체와 같다. 책임: Cart를 Order로 변환
 */

public class CartToOrderMapper {

  public Order mapFrom(LoginUser user, Cart cart) {
    return new Order(
        user.getUserId(),
        cart.getReceiverName(),
        cart.getReceiverPhone(),
        cart.getReceiverAddress(),
        OrderStatus.ORDER_CREATED,
        cart.getCartItems().stream().map(this::toOrderItem)
            .collect(toList()));

  }

  public OrderItem toOrderItem(Cart.CartItem cartItem) {
    return new OrderItem(cartItem.getProductId(),
        cartItem.getName(),
        cartItem.getPrice(),
        cartItem.getCount(),
        cartItem.getGroups().stream().map(this::toOrderItemOptionGroup).collect(toList())
    );
  }

  public OrderItemOptionGroup toOrderItemOptionGroup(Cart.CartItemOptionGroup cartItemOptionGroup) {
    return new OrderItemOptionGroup(
        cartItemOptionGroup.getProductOptionGroupId(),
        cartItemOptionGroup.getName(),
        cartItemOptionGroup.getOptions().stream().map(this::toOrderItemOption).collect(toList()));
  }

  public OrderItemOption toOrderItemOption(Cart.CartItemOption cartItemOption) {
    return new OrderItemOption(cartItemOption.getProductOptionId(),
        cartItemOption.getName(),
        cartItemOption.getPrice());
  }
}
