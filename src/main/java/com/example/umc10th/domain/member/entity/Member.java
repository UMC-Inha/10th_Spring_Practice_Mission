package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.enums.Gender;
import com.example.umc10th.domain.member.enums.SocialProvider;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="user")
public class Member {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "user_id", nullable = false)
   private Long id;

   @Column(name = "user_name", nullable = false)
   private String name;

   @Column(name = "gender", nullable = false)
   @Enumerated(EnumType.STRING)
   private Gender gender;

   @Column(name = "birth", nullable = false)
   private LocalDateTime birth;

   @Column(name = "address", nullable = false)
   private String address;

   @Column(name = "social_uid", nullable = false)
   private String socialUid;

   @Column(name = "social_provider", nullable = false)
   @Enumerated(EnumType.STRING)
   private SocialProvider socialProvider;

   @Column(name = "point", nullable = false)
   private Integer point;
}
