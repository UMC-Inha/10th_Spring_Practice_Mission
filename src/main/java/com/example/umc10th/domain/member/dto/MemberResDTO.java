package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;
import lombok.Builder;

import java.time.LocalDateTime;

public class MemberResDTO {

   @Builder
   public record SignUp(
       Long id,
       LocalDateTime createdAt
   ){}

   @Builder
   public record Login(
       String accessToken,
       Long memberId
   ){}

   @Builder
    public record MyPage(
        String name,
        String email,
        Long point
    ) {}
}
