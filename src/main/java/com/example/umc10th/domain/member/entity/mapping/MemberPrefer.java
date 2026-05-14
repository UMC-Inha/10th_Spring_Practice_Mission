package com.example.umc10th.domain.member.entity.mapping;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.Food;
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
@Table(name = "preferred_food")
public class MemberPrefer {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "preferred_food_id", nullable = false)
   private Long id;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "user_id", nullable = false)
   private Member member;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "food_id", nullable = false)
   private Food food;
}
