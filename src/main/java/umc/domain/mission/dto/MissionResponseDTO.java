package umc.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MissionResponseDTO {

    // 미션 목록 조회용(진행중/진행완료) - 각 미션별 정보
    @Builder
    public record MissionItem(
            Long storeId,
            String storeName,
            Long missionId,
            String missionCondition,
            Integer rewardPoint
    ) {}

    // 미션 목록 조회용(진행중/진행완료) -페이지네이션 데이터
    @Builder
    public record MissionList(
            List<MissionItem> missions,
            Integer pageNumber,
            Integer pageSize,
            Long totalElements, // 전체 데이터 개수
            Integer totalPages, // 전체 페이지 수
            Boolean hasNext     // 다음 페이지 존재 여부
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

    @Builder
    public record SliceResponse<T>(
        List<T> data,
        Boolean hasNext,
        String nextCursor,
        Integer pageSize
    ) {}
}
