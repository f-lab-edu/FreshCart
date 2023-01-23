package com.example.freshcart.order.presentation.request;

import static java.util.stream.Collectors.toList;

import com.example.freshcart.order.application.command.CartCommand;
import com.example.freshcart.order.application.command.CartCommand.CartItemCommand;
import com.example.freshcart.order.application.command.CartCommand.CartItemOptionCommand;
import java.util.List;
import lombok.Builder;
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

  @Builder
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
    private int count;
    private List<CartItemOption> groups;

    @Builder
    public CartItem(Long productId, int count,
        List<CartItemOption> groups) {
      this.productId = productId;
      this.count = count;
      this.groups = groups;
    }

    public CartItemCommand toCartItemCommand() {
      if (groups == null) {
        return new CartItemCommand(this.productId, this.count);
      } else {
        return new CartItemCommand(this.productId, this.count,
            this.groups.stream().map(CartItemOption::toCartItemOptionCommand)
                .collect(toList()));
      }
    }
  }


  @Getter
  @NoArgsConstructor
  public static class CartItemOption {

    private Long optionId;

    @Builder
    public CartItemOption(Long optionId) {
      this.optionId = optionId;
    }

    public CartItemOptionCommand toCartItemOptionCommand() {
      return new CartItemOptionCommand(this.optionId);
    }

  }


}
