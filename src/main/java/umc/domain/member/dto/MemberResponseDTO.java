package umc.domain.member.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

public class MemberResponseDTO {

    @Builder
    public record SignUpDTO(
            Long memberId,
            LocalDateTime createdAt
    ) {}

    @Builder
    public record HomeDTO(
            String region,
            Integer point,
            Integer missionCompletedCount,
            Integer missionGoalCount,
            Integer missionPoint,
            List<MissionDTO> mission
    ) {
        @Builder
        public record MissionDTO(
                LocalDate deadLine,
                String missionCondition,
                Integer rewardPoint,
                String storeName,
                String storeFood
        ) {}
    }

    @Builder
    public record MyPageDTO(
            Long id,
            String name,
            String email,
            String phoneNumber,
            Integer points,
            String profileUrl
    ) {}
}
