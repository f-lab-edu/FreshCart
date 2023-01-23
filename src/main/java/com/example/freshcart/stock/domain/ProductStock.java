package com.example.freshcart.stock.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product_inventory")
@NoArgsConstructor
@Getter
public class ProductStock {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;
  private Long productId;
  private int quantity;
  private Long sellerId;

  public ProductStock(Long productId, int quantity, Long sellerId) {
    this.productId = productId;
    this.quantity = quantity;
    this.sellerId = sellerId;
  }

  public void changeStock(int quantity) {
    this.quantity = quantity;
  }

  public void reduceStock(int quantity) {
    this.quantity -= quantity;
  }
}
