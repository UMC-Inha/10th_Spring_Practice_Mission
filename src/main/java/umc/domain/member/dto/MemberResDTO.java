package umc.domain.member.dto;

import lombok.Builder;
import umc.domain.member.enums.Gender;
import umc.domain.member.enums.Social_Type;
import umc.domain.member.enums.Status;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MemberResDTO {

    // Member 조회
    @Builder
    public record GetMemberDTO(
            Long member_id,
            String email,
            String name,
            Gender gender,
            String birth,
            String phone,
            Integer point,
            Status status
    ){}

    // 회원가입
    @Builder
    public record GetSignUpDTO(
            Long member_id,
            LocalDateTime createdAt
    ) {
    }

    // 로그인
    public record LoginResponse(
            String accessToken
    ) {}

    // 내 미션 조회
    @Builder
    public record GetMemberMissionDTO(
            Long member_id,
            Long mission_id,
            String succ_yn,
            LocalDate user_start_dt
    ){}


}