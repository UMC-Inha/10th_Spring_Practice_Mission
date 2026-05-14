package umc.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDate;
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
            Integer missionCompletedCount,
            Integer missionGoalCount,
            Integer missionPoint,
            List<HomeMissionItem> mission
    ) {}

    // 가게 내 미션 조회
    @Builder
    public record GetMission(
            LocalDate deadLine,
            Integer rewardPoint,
            String missionCondition
    ) {}

    // 페이지네이션 틀
    @Builder
    public record Pagination<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ) {}
}
