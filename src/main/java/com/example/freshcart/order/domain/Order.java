package com.example.freshcart.order.domain;

import com.example.freshcart.global.domain.Timestamped;
import java.time.LocalDateTime;
import java.util.List;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "\"order\"")
@Getter
@NoArgsConstructor
public class Order extends Timestamped {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long userId;

  private String receiverName;

  private String receiverPhone;

  private String receiverAddress;
  @Enumerated
  private OrderStatus orderStatus;
  @OneToMany
  @JoinColumn(name="id")
  private List<OrderItem> orderItem;


  public Order(Long userId, String receiverName, String receiverPhone,
      String receiverAddress, OrderStatus orderStatus,
      List<OrderItem> orderItem) {
    this.userId = userId;
    this.receiverName = receiverName;
    this.receiverPhone = receiverPhone;
    this.receiverAddress = receiverAddress;
    this.orderStatus = orderStatus;
    this.orderItem = orderItem;
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
