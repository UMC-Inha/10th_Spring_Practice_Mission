package umc.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDate;

public class MissionResDTO {

    // 가게 내 미션 조회
    @Builder
    public record GetMission(
            Long missionId,
            Integer reward_point,
            String conditional,
            LocalDate start_dt,
            LocalDate end_dt
    ){}
}
