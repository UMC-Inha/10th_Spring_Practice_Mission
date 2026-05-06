package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.enums.TermsType;
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
@Table(name="terms")
public class Terms extends BaseEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name="terms_id", nullable = false)
   private Long id;

   @Column(name = "terms_title", nullable = false)
   private String termsTitle;

   @Column(name = "terms_content", nullable = false)
   private String termsContent;

   @Column(name = "terms_type", nullable = false)
   @Enumerated(EnumType.STRING)
   private TermsType termsType;

   @Column(name="terms_version", nullable = false)
   private String termsVersion;
}
