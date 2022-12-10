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
@MappedSuperclass // 멤버 변수가 컬럼이 되도록 합니다.
@EntityListeners(AuditingEntityListener.class) // 변경되었을 때 자동으로 기록합니다.
public abstract class Timestamped {
  @Column(name = "created_at")
  @CreatedDate // 최초 생성 시점
  private LocalDateTime createdAt;
  @Column(name = "updated_at")
  @LastModifiedDate // 마지막 변경 시점
  private LocalDateTime updatedAt;
}
