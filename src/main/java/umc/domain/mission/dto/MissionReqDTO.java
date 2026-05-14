package umc.domain.mission.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import umc.domain.mission.enums.MemberMissionStatus;

import java.time.LocalDate;

public class MissionReqDTO {

    public record UpdateMissionStatus(
            MemberMissionStatus status
    ) {}

    public record CreateMission(
            @NotNull(message = "마감기한은 필수입니다.")
            LocalDate deadLine,
            @NotNull(message = "미션 성공 포인트는 필수입니다.")
            Integer point,
            @NotBlank(message = "조건은 빈칸일 수 없습니다.")
            String conditional
    ) {}

    // 진행중인 미션 조회용
    public record GetMyMissionInProgress(
            Long memberId,
            Integer pageNumber,
            Integer pageSize
    ) {}
}
