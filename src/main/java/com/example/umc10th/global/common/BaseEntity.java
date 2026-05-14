package com.example.umc10th.global.common;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import java.time.LocalDateTime;

@MappedSuperclass // 부모 클래스가 가지는 칼럼만 매핑정보로 제공하고 싶다
@EntityListeners(AutoCloseable.class) // 특정 이벤트 발생 시, 이를 감지하여 자동으로 특정 작업 수행
@Getter
public abstract class BaseEntity {

   @CreatedDate
   @Column(name = "created_at", nullable = false)
   private LocalDateTime createdAt;

   @LastModifiedDate
   @Column(name = "last_modified_at")
   private LocalDateTime updateAt;

   @Column(name = "deleted_at")
   private LocalDateTime deleteAt;
}
