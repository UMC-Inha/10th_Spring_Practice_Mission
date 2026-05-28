package umc.domain.mission.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public class MissionReqDTO {

    // 가게 미션 생성
    public record CreateMissionDTO(
            @NotNull
            Integer reward_point,
            @NotBlank
            String conditional,
            @NotNull
            LocalDate start_dt,
            @NotNull
            LocalDate end_dt

    ){}
}
