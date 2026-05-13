package umc.domain.mission.converter;

import org.springframework.data.domain.Page;
import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.entity.mapping.MemberMission;

import java.util.List;

public class MissionConverter {

    public static List<MissionResDTO.MissionDto> toMissionResDTO(List<MemberMission> memberMissions){
        return memberMissions.stream()
                .map(mm -> MissionResDTO.MissionDto.builder()
                        .memberMissionId(mm.getId())
                        .missionId(mm.getMission().getId())
                        .missionDescription(mm.getMission().getDescription())
                        .missionPoints(mm.getMission().getPoints())
                        .missionStatus(mm.getMissionStatus())
                        .storeId(mm.getMission().getStore().getId())
                        .storeName(mm.getMission().getStore().getName())
                        .build()
                )
                .toList();
    }

    public static MissionResDTO.CursorPage toCursorPage(List<MemberMission> results, boolean hasNext) {
        MemberMission last = results.isEmpty() ? null : results.get(results.size() - 1);

        return MissionResDTO.CursorPage.<MissionResDTO.MissionDto>builder()
                .data(toMissionResDTO(results))
                .nextCursorId(hasNext ? last.getId() : null)
                .nextCursorDueDate(hasNext ? last.getDueDate() : null)
                .hasNext(hasNext)
                .build();
    }

    public static MissionResDTO.OffsetPage toOffsetPage(Page<MemberMission> results) {
        return MissionResDTO.OffsetPage.<MissionResDTO.MissionDto>builder()
                .data(toMissionResDTO(results.getContent()))
                .pageNumber(results.getNumber())
                .pageSize(results.getSize())
                .totalPages(results.getTotalPages())
                .totalElements(results.getTotalElements())
                .build();
    }
}
