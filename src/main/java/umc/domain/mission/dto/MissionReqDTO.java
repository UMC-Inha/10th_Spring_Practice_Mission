package umc.domain.mission.dto;

public class MissionReqDTO {

    public record MissionStatusUpdate(
            boolean is_completed
    ){}

}
