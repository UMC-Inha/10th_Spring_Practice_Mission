package com.example.umc10th.domain.member.dto;

import com.example.umc10th.domain.member.enums.Gender;

import java.time.LocalDate;

public class MemberReqDTO {

   public record SignUp(
       String name,
       Gender gender,
       LocalDate birth,
       String address,
       String email,
       String password
   ){}

   public record Login(
       String email,
       String password
   ){}

   public record GetMyPage(
        Long id
    ) {}
}
