package com.example.umc10th.domain.member.exception;

import com.example.umc10th.global.apiPayload.code.BaseSuccessCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

   GET_MY_PAGE(HttpStatus.OK,
       "MEMBER200_1",
       "마이페이지가 조회되었습니다."),
   LOGIN_SUCCESS(HttpStatus.OK,
       "MEMBER200_2",
       "성공적으로 로그인 하였습니다."),
   SIGN_UP(HttpStatus.CREATED,
       "MEMBER201_1",
       "성공적으로 회원가입 하였습니다."),
   ;

   private final HttpStatus status;
   private final String code;
   private final String message;
}
