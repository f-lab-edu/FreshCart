package com.example.freshcart.global.domain;

import java.time.LocalDateTime;
import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class Timestamped {
  @Column(name = "created_at")
  @CreatedDate
  private LocalDateTime createdAt;
  @Column(name = "updated_at")
  @LastModifiedDate
  private LocalDateTime updatedAt;
}
