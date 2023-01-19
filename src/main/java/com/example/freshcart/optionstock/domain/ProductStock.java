package com.example.freshcart.optionstock.domain;

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
  private int stock;
  private Long sellerId;

  public ProductStock(Long productId, int stock, Long sellerId) {
    this.productId = productId;
    this.stock = stock;
    this.sellerId = sellerId;
  }

  public void changeStock(int stock) {
    this.stock = stock;
  }

  public void reduceStock(int stock) {
    this.stock -= stock;
  }
}
