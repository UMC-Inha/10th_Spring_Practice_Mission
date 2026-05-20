package umc.mission.dto;

public class MissionReqDTO {

    public record CreateMission(
            Integer deadline,
            Integer point,
            String content
    ){}

    public record MemberIdRequest(
            Long memberId
    ) {}
}
