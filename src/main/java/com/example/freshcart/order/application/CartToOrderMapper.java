package com.example.freshcart.order.application;

import static java.util.stream.Collectors.toList;

import com.example.freshcart.authentication.application.LoginUser;
import com.example.freshcart.order.application.command.CartCommand;
import com.example.freshcart.order.domain.Order;
import com.example.freshcart.order.domain.Order.OrderStatus;
import com.example.freshcart.order.domain.OrderItem;
import com.example.freshcart.order.domain.OrderItemOption;
import com.example.freshcart.order.domain.OrderItemOptionGroup;

/**
 * 카트는 데이터를 담아두는 DTO 객체와 같다. 책임: Cart를 Order로 변환
 */

public class CartToOrderMapper {

  public Order mapFrom(LoginUser user, CartCommand cart) {
    return new Order(
        user.getUserId(),
        cart.getReceiverName(),
        cart.getReceiverPhone(),
        cart.getReceiverAddress(),
        OrderStatus.ORDER_CREATED,
        cart.getCartItems().stream().map(this::toOrderItem)
            .collect(toList()));

  }

  public OrderItem toOrderItem(CartCommand.CartItemCommand cartItem) {
    return new OrderItem(cartItem.getProductId(),
        cartItem.getName(),
        cartItem.getPrice(),
        cartItem.getCount(),
        cartItem.getGroups().stream().map(this::toOrderItemOptionGroup).collect(toList())
    );
  }

  public OrderItemOptionGroup toOrderItemOptionGroup(CartCommand.CartItemOptionGroupCommand cartItemOptionGroup) {
    return new OrderItemOptionGroup(
        cartItemOptionGroup.getOptionGroupId(),
        cartItemOptionGroup.getName(),
        cartItemOptionGroup.getOptions().stream().map(this::toOrderItemOption).collect(toList()));
  }

  public OrderItemOption toOrderItemOption(CartCommand.CartItemOptionCommand cartItemOption) {
    return new OrderItemOption(cartItemOption.getOptionId(),
        cartItemOption.getName(),
        cartItemOption.getPrice());
  }
}
