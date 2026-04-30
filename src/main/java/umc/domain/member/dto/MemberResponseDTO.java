package umc.domain.member.dto;

import lombok.Builder;

public class MemberResponseDTO {

    @Builder
    public record GetInfo(
            String name,
            String email,
            String phoneNumber,
            String profileUrl,
            Integer point
    ) {}
}
