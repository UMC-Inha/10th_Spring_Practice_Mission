package umc.domain.mission.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
            LocalDateTime dueDate,
            Long storeId,
            String storeName,
            String storeCategory
    ){}

}
