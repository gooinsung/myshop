package com.shop.myshop.data.entity;

import jakarta.persistence.Column;
import jakarta.persistence.MappedSuperclass;
import java.time.LocalDateTime;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@MappedSuperclass
public class BaseEntity {
  @Column(name = "CREATED_AT")
  @CreationTimestamp
  private LocalDateTime createdAt;
  @Column(name = "UPDATED_AT")
  @UpdateTimestamp
  private LocalDateTime updatedAt;
  @Column(name = "IS_DELETED")
  @ColumnDefault("false")
  private Boolean isDeleted;

}
