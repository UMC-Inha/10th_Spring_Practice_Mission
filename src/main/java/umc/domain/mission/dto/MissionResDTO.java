package umc.domain.mission.dto;

import lombok.Builder;
import umc.domain.mission.enums.MissionStatus;

import java.time.LocalDate;
import java.util.List;

public class MissionResDTO {

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

    @Builder
    public record CursorPage<T>(
            List<T> data,
            Long nextCursorId,
            LocalDate nextCursorDueDate,
            boolean hasNext
    ) {}

    @Builder
    public record OffsetPage<T>(
            List<T> data,
            int pageNumber,
            int pageSize,
            int totalPages,
            long totalElements
    ) {}

    @Builder
    public record MissionStatusUpdateDTO(
            Long memberMissionId,
            String missionDescription,
            MissionStatus missionStatus
    ) {}
}
