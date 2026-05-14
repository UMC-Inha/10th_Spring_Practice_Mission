package umc.domain.mission.converter;

import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.entity.mapping.MemberMission;

import java.util.List;

public class MissionConverter {

    public static MissionResDTO.MissionListDto toMissionListDto(List<MemberMission> results, boolean hasNext) {
        MemberMission last = results.isEmpty() ? null : results.get(results.size() - 1);

        List<MissionResDTO.MissionListDto.MissionDto> missionDtos = results.stream()
                .map(mm -> MissionResDTO.MissionListDto.MissionDto.builder()
                        .memberMissionId(mm.getId())
                        .missionId(mm.getMission().getId())
                        .missionDescription(mm.getMission().getDescription())
                        .missionPoints(mm.getMission().getPoints())
                        .missionStatus(mm.getMissionStatus())
                        .storeId(mm.getMission().getStore().getId())
                        .storeName(mm.getMission().getStore().getName())
                        .build())
                .toList();

        return MissionResDTO.MissionListDto.builder()
                .missionList(missionDtos)
                .nextCursorId(hasNext ? last.getId() : null)
                .nextCursorDueDate(hasNext ? last.getDueDate() : null)
                .hasNext(hasNext)
                .build();
    }
}
