package com.example.freshcart.order.application.command;

import java.util.List;
import lombok.Builder;
import lombok.Getter;

/**
 * presentation 계층의 Cart를 바로 쓰지 않고, application 층 내에서 Command 로 변환해서 사용
 */

@Getter
public class CartCommand {

  private String receiverName;

  private String receiverPhone;

  private String receiverAddress;

  private List<CartItemCommand> cartItems;


  public CartCommand(String receiverName, String receiverPhone, String receiverAddress,
      List<CartItemCommand> cartItems) {
    this.receiverName = receiverName;
    this.receiverPhone = receiverPhone;
    this.receiverAddress = receiverAddress;
    this.cartItems = cartItems;
  }

  /**
   * CartItem은 Product, OrderLineItem과 매칭
   */
  @Getter
  public static class CartItemCommand {

    private Long productId;
    private int count;
    private List<CartItemOptionCommand> groups;

    @Builder
    public CartItemCommand(Long productId, int count,
        List<CartItemOptionCommand> groups) {
      this.productId = productId;
      this.count = count;
      this.groups = groups;
    }

    public CartItemCommand(Long productId, int count) {
      this.productId = productId;
      this.count = count;
    }
  }


  @Getter
  public static class CartItemOptionCommand {

    private Long optionId;

    public CartItemOptionCommand(Long optionId) {
      this.optionId = optionId;
    }
  }
}
