package umc.domain.mission.dto;

import lombok.Builder;
import umc.domain.mission.enums.MemberMissionStatus;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {

    @Builder
    public record GetMissions(
            List<MissionPreview> missions
    ){}

    @Builder
    public record MissionPreview(
            Long memberMissionId,
            Long storeId,
            String storeName,
            String storeCategory,
            String missionContent,
            Long rewardPoint,
            MemberMissionStatus status,
            LocalDate dueDate
    ) {}

    @Builder
    public record UpdateMissionStatus(
            Long memberMissionId,
            MemberMissionStatus status,
            Integer rewardPoint
    ) {}
}
