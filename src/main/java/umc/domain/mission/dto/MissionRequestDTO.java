package umc.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDate;

public class MissionRequestDTO {

    // 가게 미션 생성
    @Builder
    public record CreateMission(
            LocalDate deadLine,
            Integer rewardPoint,
            String missionCondition
    ) {}
}
