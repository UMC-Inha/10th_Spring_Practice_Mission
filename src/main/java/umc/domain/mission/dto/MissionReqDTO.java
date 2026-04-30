package umc.domain.mission.dto;

import umc.domain.mission.enums.MemberMissionStatus;

public class MissionReqDTO {

    public record UpdateMissionStatus(
            MemberMissionStatus status
    ) {}
}
