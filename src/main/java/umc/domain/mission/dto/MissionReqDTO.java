package umc.domain.mission.dto;

import umc.domain.mission.enums.MissionStatus;

public class MissionReqDTO {

    public record MissionStatusUpdateDTO(
            MissionStatus missionStatus
    ) {}
}
