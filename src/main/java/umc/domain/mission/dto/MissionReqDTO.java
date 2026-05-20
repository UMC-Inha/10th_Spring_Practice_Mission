package umc.domain.mission.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import umc.domain.mission.enums.MissionStatus;

public class MissionReqDTO {

    public record MissionStatusUpdateDTO(
            @NotNull
            MissionStatus missionStatus
    ) {}

    public record MissionViewDTO(
            @NotNull
            @Positive
            Long memberId
    ) {}
}
