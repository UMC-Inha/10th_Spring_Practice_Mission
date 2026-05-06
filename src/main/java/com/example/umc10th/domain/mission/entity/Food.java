package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.mission.enums.FoodName;
import com.example.umc10th.global.common.BaseEntity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "food")
public class Food extends BaseEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "food_id", nullable = false)
   private Long id;

   @Column(name = "food_name", nullable = false)
   @Enumerated(EnumType.STRING)
   private FoodName foodName;
}
