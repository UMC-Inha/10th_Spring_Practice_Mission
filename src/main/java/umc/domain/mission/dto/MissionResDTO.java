package umc.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {

    //미션 리스트 조회
    @Builder
    public record MissionList(
            List<MissionDTO> missions
    ){}

    @Builder
    public record MissionDTO(
            Long missionId,
            int condition,
            int missionPoint,
            LocalDate dueDate,
            Long storeId,
            String storeName,
            String storeCategory
    ){}

    //페이지네이션 틀
    @Builder
    public record Pagination<T>(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){}

}
