package com.example.umc10th.domain.review.entity;

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
@Table(name = "store_image")
public class StoreImage extends BaseEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "store_image_id", nullable = false)
   private Long id;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "store_id", nullable = false)
   private Store store;

   @Column(name = "store_image_url")
   private String storeImageUrl;

   @Column(name = "store_image_size")
   private String storeImageSize;
}
