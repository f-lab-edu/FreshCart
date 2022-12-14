package com.example.freshcart.order.application.command;

import java.util.List;
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
  public static class CartItemCommand {

    private Long productId;
    private String name;
    private int price;
    private int count;
    private List<CartItemOptionGroupCommand> groups;

    public CartItemCommand(Long productId, String name, int price, int count,
        List<CartItemOptionGroupCommand> groups) {
      this.productId = productId;
      this.name = name;
      this.price = price;
      this.count = count;
      this.groups = groups;
    }

    public Long getProductId() {
      return productId;
    }

    public String getName() {
      return name;
    }

    public int getCount() {
      return count;
    }

    public int getPrice() {
      return price;
    }

    public List<CartItemOptionGroupCommand> getGroups() {
      return groups;
    }
  }

  public static class CartItemOptionGroupCommand {

    private Long optionGroupId;
    private String name;
    private List<CartItemOptionCommand> options;

    public CartItemOptionGroupCommand(Long optionGroupId, String name,
        List<CartItemOptionCommand> options) {
      this.optionGroupId = optionGroupId;
      this.name = name;
      this.options = options;
    }

    public Long getOptionGroupId() {
      return optionGroupId;
    }

    public String getName() {
      return name;
    }

    public List<CartItemOptionCommand> getOptions() {
      return options;
    }


  }

  public static class CartItemOptionCommand {

    private Long optionId;
    private String name;
    private int price;

    public CartItemOptionCommand(Long optionId, String name, int price) {
      this.optionId = optionId;
      this.name = name;
      this.price = price;
    }

    public Long getOptionId() {
      return optionId;
    }

    public String getName() {
      return name;
    }

    public int getPrice() {
      return price;
    }
  }
}
