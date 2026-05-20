package umc.domain.mission.dto;

import java.time.LocalDate;

public class MissionReqDTO {

    // 가게 미션 생성
    public record CreateMission(
            Integer reward_point,
            String conditional,
            LocalDate start_dt,
            LocalDate end_dt

    ){}
}
