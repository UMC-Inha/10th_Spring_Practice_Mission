package com.example.umc10th.domain.member.exception;

import com.example.umc10th.global.apiPayload.code.BaseErrorCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

   // 400
   INVALID_PASSWORD(HttpStatus.NOT_FOUND,
       "MEMBER404_2",
       "이메일 또는 비밀번호가 일치하지 않습니다."),
   // 404
   MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND,
       "MEMBER404_1",
       "멤버를 찾을 수 없습니다."),
   // 409
   DUPLICATE_EMAIL(HttpStatus.CONFLICT,
       "MEMBER409_1",
       "이미 존재하는 이메일입니다."),
   ;

   private final HttpStatus status;
   private final String code;
   private final String message;
}
