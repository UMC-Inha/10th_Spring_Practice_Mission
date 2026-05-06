package com.example.umc10th.domain.mission.entity;

import com.example.umc10th.domain.mission.enums.IsActive;
import com.example.umc10th.domain.review.entity.Store;
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
@Table(name = "mission")
public class Mission extends BaseEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "mission_id", nullable = false)
   private Long id;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "store_id", nullable = false)
   private Store store;

   @Column(name = "mission_content", nullable = false)
   private String missionContent;

   @Column(name = "mission_point", nullable = false)
   private Integer missionPoint;

   @Column(name = "is_active", nullable = false)
   @Enumerated(EnumType.STRING)
   private IsActive isActive;
}
