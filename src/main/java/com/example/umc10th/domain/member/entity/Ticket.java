package com.example.umc10th.domain.member.entity;

import com.example.umc10th.domain.member.enums.IsAnswered;
import com.example.umc10th.domain.member.enums.TicketCategory;
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
@Table(name = "ticket")
public class Ticket extends BaseEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ticket_id", nullable = false)
   private Long id;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "user_id", nullable = false)
   private Member member;

   @Column(name = "ticket_title", nullable = false)
   private String ticketTitle;

   @Column(name = "ticket_category", nullable = false)
   private TicketCategory ticketCategory;

   @Column(name = "ticket_content",nullable = false)
   private String ticketContent;

   @Column(name = "is_answered", nullable = false)
   private IsAnswered isAnswered;
}
