package umc.domain.mission.dto;

import lombok.Builder;
import umc.domain.mission.enums.MissionStatus;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {

    @Builder
    public record MissionListDto(
            List<MissionDto> missionList,
            Long nextCursorId,
            LocalDate nextCursorDueDate,
            boolean hasNext
    ) {

        @Builder
        public record MissionDto(
                Long memberMissionId,
                Long missionId,
                String missionDescription,
                Integer missionPoints,
                MissionStatus missionStatus,
                Long storeId,
                String storeName
        ) {}
    }

    @Builder
    public record MissionStatusUpdateDTO(
            Long memberMissionId,
            String missionDescription,
            MissionStatus missionStatus
    ) {}
}
