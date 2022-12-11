package com.example.freshcart.order.presentation.request;

import static java.util.stream.Collectors.toList;

import com.example.freshcart.order.application.command.CartCommand;
import com.example.freshcart.order.application.command.CartCommand.CartItemCommand;
import com.example.freshcart.order.application.command.CartCommand.CartItemOptionCommand;
import com.example.freshcart.order.application.command.CartCommand.CartItemOptionGroupCommand;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 * Order Command에 필요한 정보가 모두 담겼는지 확인 필요.
 */

@Getter
@NoArgsConstructor
public class Cart {

  private String receiverName;

  private String receiverPhone;

  private String receiverAddress;

  private List<CartItem> cartItems;

  public Cart(String receiverName, String receiverPhone, String receiverAddress,
      List<CartItem> cartItems) {
    this.receiverName = receiverName;
    this.receiverPhone = receiverPhone;
    this.receiverAddress = receiverAddress;
    this.cartItems = cartItems;
  }

  public CartCommand toCommand() {
    return new CartCommand(this.receiverName, this.receiverPhone, this.receiverAddress,
        this.cartItems.stream().map(CartItem::toCartItemCommand).collect(toList()));
  }

  /**
   * CartItem은 Product, OrderLineItem과 매칭
   */
  @Getter
  @NoArgsConstructor
  public static class CartItem {

    private Long productId;
    private String name;
    private int price;
    private int count;
    private List<CartItemOptionGroup> groups;

    public CartItem(Long productId, String name, int price, int count,
        List<CartItemOptionGroup> groups) {
      this.productId = productId;
      this.name = name;
      this.price = price;
      this.count = count;
      this.groups = groups;
    }

    public CartItemCommand toCartItemCommand() {
      return new CartItemCommand(this.productId, this.name, this.price, this.count,
          this.groups.stream().map(CartItemOptionGroup::toCartItemOptionGroupCommand)
              .collect(toList()));
    }

  }

  @Getter
  @NoArgsConstructor
  public static class CartItemOptionGroup {

    private Long productOptionGroupId;
    private String name;
    private List<CartItemOption> options;

    public CartItemOptionGroup(String name, Long productOptionGroupId,
        List<CartItemOption> options) {
      this.name = name;
      this.productOptionGroupId = productOptionGroupId;
      this.options = options;
    }


    public CartItemOptionGroupCommand toCartItemOptionGroupCommand() {
      return new CartItemOptionGroupCommand(this.productOptionGroupId, this.name,
          this.options.stream().map(CartItemOption::toCartItemOptionCommand).collect(toList()));
    }
  }

  @Getter
  @NoArgsConstructor
  public static class CartItemOption {

    private Long productOptionId;
    private String name;
    private int price;

    public CartItemOption(Long productOptionId, String name, int price) {
      this.productOptionId = productOptionId;
      this.name = name;
      this.price = price;
    }


    public CartItemOptionCommand toCartItemOptionCommand() {
      return new CartItemOptionCommand(this.productOptionId, this.name, this.price);
    }

  }


}
