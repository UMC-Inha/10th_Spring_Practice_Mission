package umc.domain.mission.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

import java.time.LocalDate;

public class MissionRequestDTO {

    // 가게 미션 생성
    @Builder
    public record CreateMission(
            @NotNull
            LocalDate deadLine,
            @NotNull
            @Min(1)
            Integer rewardPoint,
            @NotBlank
            String missionCondition
    ) {}

    // 내가 진행중인 미션 조회 - Request Body로 사용자 ID 받기 (추후 Path Variable 방식으로 변경 고민)
    public record GetMyMissions(
            @NotNull
            Long memberId
    ) {}
}
