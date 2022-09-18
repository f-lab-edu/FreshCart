package com.example.freshcart.product.domain;

import java.time.LocalDateTime;

public class Product {

  private int id;
  private String name;
  private int price;
  private Status status;
  //CATEGORY_ID
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
  private String description;
  private Boolean singleType;

  public enum Status {
    BEING_PREPARED, AVAILABLE, UNAVAILABLE,
  }

  //생성자 from으로.

}
