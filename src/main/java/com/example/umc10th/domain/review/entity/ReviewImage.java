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
@Table(name = "review_image")
public class ReviewImage extends BaseEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "review_image_id", nullable = false)
   private Long id;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "review_id", nullable = false)
   private Review review;

   @Column(name = "review_image_url")
   private String reviewImageUrl;

   @Column(name = "review_image_size")
   private String reviewImageSize;
}
