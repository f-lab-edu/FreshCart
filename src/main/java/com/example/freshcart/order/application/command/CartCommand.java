package com.example.freshcart.order.application.command;

import java.util.List;

/**
 * presentation 계층의 Cart를 바로 쓰지 않고, application 층 내에서 Command 로 변환해서 사용
 */
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

  public String getReceiverName() {
    return receiverName;
  }

  public String getReceiverPhone() {
    return receiverPhone;
  }

  public String getReceiverAddress() {
    return receiverAddress;
  }

  public List<CartItemCommand> getCartItemCommands() {
    return cartItems;
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

    private Long productOptionGroupId;
    private String name;
    private List<CartItemOptionCommand> options;

    public CartItemOptionGroupCommand(Long productOptionGroupId, String name,
        List<CartItemOptionCommand> options) {
      this.productOptionGroupId = productOptionGroupId;
      this.name = name;
      this.options = options;
    }

    public Long getProductOptionGroupId() {
      return productOptionGroupId;
    }

    public String getName() {
      return name;
    }

    public List<CartItemOptionCommand> getOptions() {
      return options;
    }


  }

  public static class CartItemOptionCommand {

    private Long productOptionId;
    private String name;
    private int price;

    public CartItemOptionCommand(Long productOptionId, String name, int price) {
      this.productOptionId = productOptionId;
      this.name = name;
      this.price = price;
    }

    public Long getProductOptionId() {
      return productOptionId;
    }

    public String getName() {
      return name;
    }

    public int getPrice() {
      return price;
    }
  }
}
