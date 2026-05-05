package umc.member.dto;

import lombok.Builder;

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
                String email,
                String name
        ) {}
    }
}
