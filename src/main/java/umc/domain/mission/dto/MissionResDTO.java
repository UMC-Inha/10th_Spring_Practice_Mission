package umc.domain.mission.dto;

import lombok.Builder;
import umc.domain.mission.enums.MemberMissionStatus;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {

    @Builder
    public record GetMissions(
            List<MissionPreview> missions
    ){}

    @Builder
    public record MissionPreview(
            Long memberMissionId,
            Long storeId,
            String storeName,
            String storeCategory,
            String missionContent,
            Long rewardPoint,
            MemberMissionStatus status,
            LocalDate dueDate
    ) {}

    @Builder
    public record UpdateMissionStatus(
            Long memberMissionId,
            MemberMissionStatus status,
            Integer rewardPoint
    ) {}

    @Builder
    public record GetMission(
            Long missionId,
            Integer point,
            String conditional
    ) {}

    /*
    //오프셋 페이징 페이지네이션 틀
    @Builder
    public record Pagination<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ) {}
     */

    // 진행중인 미션 조회용 오프셋 페이지네이션
    @Builder
    public record PaginationOffset<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){}

    @Builder
    public record Pagination<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){}

}
