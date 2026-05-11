package umc.domain.mission.dto;

import java.util.List;

public class HomeResDTO {
    public record RegionMissionListDTO(
            List<RegionMissionDTO> missionList,
            Long nextCursor,
            Boolean hasNext
    ){}

    public record RegionMissionDTO(
            Long missionId,
            String storeName,
            String category,
            Integer dDay,
            String missionCount,
            Integer rewardPoint
    ){}

    public record HomeSummaryDTO(
            Integer myPoint,//사용자 레벨
            Integer currentCount,//현재 진행도
            Integer targetCount,//목표 개수
            Integer rewardPoint//달성 시 포인트
    ){}
}
