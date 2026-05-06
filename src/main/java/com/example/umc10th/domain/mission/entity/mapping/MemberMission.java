package com.example.umc10th.domain.mission.entity.mapping;

import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.mission.entity.Mission;
import com.example.umc10th.domain.mission.enums.IsCompleted;
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
@Table(name = "user_mission")
public class MemberMission {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "user_mission_id", nullable = false)
   private Long id;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "user_id", nullable = false)
   private Member member;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "mission_id", nullable = false)
   private Mission mission;

   @Column(name = "is_completed", nullable = false)
   @Enumerated(EnumType.STRING)
   private IsCompleted isCompleted;
}
