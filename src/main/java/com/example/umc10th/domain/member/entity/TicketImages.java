package com.example.umc10th.domain.member.entity;

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
@Table(name = "ticket_image")
public class TicketImages extends BaseEntity {

   @Id
   @GeneratedValue(strategy = GenerationType.IDENTITY)
   @Column(name = "ticket_image_id", nullable = false)
   private Long id;

   @ManyToOne(fetch = FetchType.LAZY)
   @JoinColumn(name = "ticket_id", nullable = false)
   private Ticket ticket;

   @Column(name = "ticket_image_url")
   private String ticketImageUrl;

   @Column(name = "ticket_image_size")
   private String ticketImageSize;
}
