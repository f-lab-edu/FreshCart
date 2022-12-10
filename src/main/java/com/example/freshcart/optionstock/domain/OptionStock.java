package com.example.freshcart.optionstock.domain;

import com.example.freshcart.global.domain.Timestamped;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;

@Entity
@Table(name = "option_inventory")
@Getter
public class OptionStock extends Timestamped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(name = "option_id")
  private Long productOptionId;

  @Column
  private int stock;

  @Column(name = "seller_id ")
  private Long sellerId;


  public OptionStock(Long sellerId, Long productOptionId, int stock) {
    this.sellerId = sellerId;
    this.productOptionId = productOptionId;
    this.stock = stock;
  }

  public OptionStock() {

  }

  public void changeStock(int stock){
    this.stock = stock;
  }
}
