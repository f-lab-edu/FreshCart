package com.example.freshcart.order.domain;

import com.example.freshcart.global.domain.Timestamped;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "order_item")
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long productId;
  private Long orderId;
  private int count;
  @OneToMany
  @JoinColumn(name="id")
  private List<OrderItemOption> orderItemOption;

  public OrderItem(Long productId, int count,
      List<OrderItemOption> orderItemOption) {
    this.productId = productId;
    this.count = count;
    this.orderItemOption = orderItemOption;
  }

  public OrderItem(Long productId, int count) {
    this.productId = productId;
    this.count = count;
  }

  public void setOrderId(Long id) {
    this.orderId = id;
  }
}
