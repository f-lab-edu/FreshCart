package com.example.freshcart.stock.domain;

import com.example.freshcart.global.domain.Timestamped;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "option_inventory")
@Getter
@NoArgsConstructor
public class OptionStock extends Timestamped {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  private Long optionId;

  private int quantity;

  private Long sellerId;


  public OptionStock(Long sellerId, Long optionId, int quantity) {
    this.sellerId = sellerId;
    this.optionId = optionId;
    this.quantity = quantity;
  }

  public void changeStock(int quantity) {
    this.quantity = quantity;
  }

  public void reduceStock(int quantity) {
    this.quantity -= quantity;

  }
}
