package umc.domain.mission.dto;

import lombok.Builder;

import java.util.List;

public class MissionResDTO {

    public record MissionPreviewListDTO(
            List<MissionPreviewDTO> missionList,
            Long nextCursor,
            Boolean hasNext
    ){}

    public record MissionPreviewDTO(
            Long missionId,
            String storeName,
            String category,
            Integer dDay,
            String missionContent,
            Integer missionPoint,

            String status
    ){}

    public record ChangeStatusDTO(
        Long userMissionId,
        String status
    ){}
}
