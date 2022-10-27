package com.example.freshcart.order.domain;

import java.time.LocalDateTime;
import java.util.List;

public class Order {

  private Long id;

  private Long userId;

  private String receiverName;

  private String receiverPhone;

  private String receiverAddress;

  private OrderStatus orderStatus;

  private LocalDateTime createdAt;

  private LocalDateTime updatedAt;

  private List<OrderItem> orderItemList;


  public Order() {
  }

  public Order(Long userId, String receiverName, String receiverPhone,
      String receiverAddress, OrderStatus orderStatus,
      List<OrderItem> orderItemList) {
    this.userId = userId;
    this.receiverName = receiverName;
    this.receiverPhone = receiverPhone;
    this.receiverAddress = receiverAddress;
    this.orderStatus = orderStatus;
    this.orderItemList = orderItemList;
  }

  public Long getId() {
    return id;
  }

  public Long getUserId() {
    return userId;
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

  public OrderStatus getOrderStatus() {
    return orderStatus;
  }

  public List<OrderItem> getOrderItemList() {
    return orderItemList;
  }

  public LocalDateTime getCreatedAt() {
    return createdAt;
  }

  public LocalDateTime getUpdatedAt() {
    return updatedAt;
  }

  public enum OrderStatus {
    ORDER_CREATED("주문시작"),
    PAYED("결제완료"),
    BEING_PREPARED("배송준비중"),
    SHIPPING("배송중"),
    DELIVERED("배송완료");

    private final String description; //enum에 대한 설명을 쓰려면 description 필요

    OrderStatus(String description) {
      this.description = description;
    }
  }
}
