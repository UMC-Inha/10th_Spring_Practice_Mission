package umc.domain.member.dto;

import java.time.LocalDateTime;

public class MemberResDTO {

    public record JoinResultDTO(
            Long userId,
            LocalDateTime createdAt
    ){}
    public record MyProfileDTO(
            String nickname,
            String profileImageUrl,
            String email,
            String phoneNumber,
            Boolean isPhoneVerified,
            Integer point
    ){}
}
