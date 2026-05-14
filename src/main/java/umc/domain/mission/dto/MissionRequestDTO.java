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

    // 내가 진행중인 미션 조회 - Request Body로 사용자 ID 받기 (추후 Path Variable 방식으로 변경 고민)
    public record GetMyMissions(
            Long memberId
    ) {}
}
