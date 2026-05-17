package umc.domain.mission.dto;

import jakarta.validation.constraints.NotNull;

public class MissionReqDTO {

    public record MissionStatusUpdate(
            @NotNull boolean is_completed
    ){}

}
