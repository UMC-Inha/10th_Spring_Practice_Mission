package com.example.umc10th.domain.review.entity;

import com.example.umc10th.domain.review.enums.StoreCategory;
import com.example.umc10th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "store")
public class Store extends BaseEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "store_id", nullable = false)
   private Long id;

   @Column(name = "store_name", nullable = false)
   private String storeName;

   @Column(name = "store_category", nullable = false)
   private StoreCategory storeCategory;

   @Column(name = "store_open_hour")
   private String storeOpenHour;

   @Column(name = "store_address")
   private String storeAddress;
}
