package com.example.freshcart.product.domain;

import java.time.LocalDateTime;
import java.util.List;

public class OptionGroup {
  private Long id;
  private String name;
  private boolean requiredOption;
  private List<Option> options;
  private LocalDateTime createdAt;
  private LocalDateTime updatedAt;
}
