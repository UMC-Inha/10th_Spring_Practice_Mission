package umc.domain.member.dto;

import java.time.LocalDateTime;

public class MemberResDTO {

    public record JoinResultDTO(
            Long userId,
            LocalDateTime createdAt
    ){}
}
