package umc.mission.dto;

import lombok.Builder;

import java.util.List;

public class MissionResDTO {

    @Builder
    public record MissionResultDTO(
        String currentRegion,
        Integer successMission,
        Integer alarmCount,
        MissionListDTO missions
    ) {}

    @Builder
    public record MissionListDTO(
            List<MissionInfoDTO> content,
            PageInfoDTO pageable,
            Boolean isFirst,
            Boolean isLast,
            Boolean hasNext
    ) {}

    @Builder
    public record PageInfoDTO(
            Integer pageNumber,
            Integer pageSize
    ) {}

    @Builder
    public record MissionInfoDTO(
            Long missionId,
            String storeName,
            String missionContent,
            Integer missionPoint,
            Integer missionDeadline
    ) {}

    @Builder
    public record MyMissionListDTO(
            List<MyMissionInfoDTO> myMissions,
            Integer listSize,
            Boolean hasNext,
            Long nextCursor
    ) {}

    @Builder
    public record MyMissionInfoDTO(
            Long userMissionId,
            String storeName,
            String missionContent,
            Integer missionPoint,
            Integer missionDeadline
    ) {}

    @Builder
    public record MissionCompleteResultDTO(
            Long userMissionId
    ) {}

    @Builder
    public record GetMission(
            Long missionId,
            Integer point,
            String content
    ){}
}
