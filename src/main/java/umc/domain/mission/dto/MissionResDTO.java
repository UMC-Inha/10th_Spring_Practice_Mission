package umc.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class MissionResDTO {

    // 미션 생성 응답
    @Builder
    public record GetCreateMissionDTO(
            Long mission_id,
            LocalDateTime createdAt
    ){}

    // 미션 조회 응답
    @Builder
    public record GetMissionDTO(
            Long missionId,
            Integer reward_point,
            String conditional,
            LocalDate start_dt,
            LocalDate end_dt
    ){}
}