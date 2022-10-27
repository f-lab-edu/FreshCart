package com.example.freshcart.order.presentation.request;

import java.util.List;

/**
 * Order Command에 필요한 정보가 모두 담겼는지 확인 필요.
 */
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

  public String getReceiverName() {
    return receiverName;
  }

  public String getReceiverPhone() {
    return receiverPhone;
  }

  public String getReceiverAddress() {
    return receiverAddress;
  }

  public List<CartItem> getCartItems() {
    return cartItems;
  }

  /**
   * CartItem은 Product, OrderLineItem과 매칭
   */
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

    public List<CartItemOptionGroup> getGroups() {
      return groups;
    }
  }

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

    public Long getProductOptionGroupId() {
      return productOptionGroupId;
    }

    public String getName() {
      return name;
    }

    public List<CartItemOption> getOptions() {
      return options;
    }


  }

  public static class CartItemOption {

    private Long productOptionId;
    private String name;
    private int price;

    public CartItemOption(Long productOptionId, String name, int price) {
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
