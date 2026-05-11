package umc.member.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class MemberResDTO {

    @Builder
    public record GetInfo(
            String name,
            String profileUrl,
            String email,
            String phoneNumber,
            Integer point
    ) {}

    public class AuthResDTO {
        @Builder
        public record SignUpResultDTO(
                Long memberId,
                String email,
                String name,
                String gender,
                String address,
                String birth,
                String socialType,
                LocalDateTime createdAt
        ) {}
    }
}
