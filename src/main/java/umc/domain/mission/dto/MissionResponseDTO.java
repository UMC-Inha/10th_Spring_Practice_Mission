package umc.domain.mission.dto;

import lombok.Builder;

import java.util.List;

public class MissionResponseDTO {

    @Builder
    public record MissionItem(
            Long storeId,
            String storeName,
            Long missionId,
            String missionCondition,
            Integer rewardPoint
    ) {}

    @Builder
    public record MissionList(
            List<MissionItem> mission
    ) {}

    @Builder
    public record HomeMissionItem(
            Long dDay,
            String missionCondition,
            Integer rewardPoint,
            String storeName,
            String storeCategory
    ) {}

    @Builder
    public record HomeMissionList(
            String region,
            Integer point,
            Long missionCompletedCount,
            Integer missionGoalCount,
            Integer missionPoint,
            List<HomeMissionItem> mission
    ) {}
}
