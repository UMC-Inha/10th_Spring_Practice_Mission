package com.example.umc10th.domain.member.controller;

import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberSuccessCode;
import com.example.umc10th.domain.member.service.MemberService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import com.example.umc10th.global.security.entity.AuthMember;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class MemberController {

   private final MemberService memberService;

   @PostMapping("/auth/signup")
   public ApiResponse<MemberResDTO.SignUp> signup(@RequestBody MemberReqDTO.SignUp dto) {
      BaseSuccessCode code = MemberSuccessCode.SIGN_UP;
      return ApiResponse.onSuccess(code, memberService.signUp(dto));
   }

   @PostMapping("/auth/login")
   public ApiResponse<MemberResDTO.Login> login(@RequestBody MemberReqDTO.Login dto){
      return ApiResponse.onSuccess(
          MemberSuccessCode.LOGIN_SUCCESS,
          memberService.login(dto)
      );
   }


   @GetMapping("/members/me")
   public ApiResponse<MemberResDTO.MyPage> getMyPage(
       @AuthenticationPrincipal AuthMember member
       ) {
         BaseSuccessCode code = MemberSuccessCode.GET_MY_PAGE;
      return ApiResponse.onSuccess(code, memberService.getMyPage(member));
   }
}
