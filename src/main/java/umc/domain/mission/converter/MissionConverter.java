package umc.domain.mission.converter;

import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.entity.Mission;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResDTO.MissionDTO toGetMissionDTO(Mission mission) {
        return MissionResDTO.MissionDTO.builder()
                .missionId(mission.getId())
                .condition(mission.getCondition())
                .missionPoint(mission.getPoint())
                .dueDate(mission.getDueDate())
                .storeId(mission.getStore().getId())
                .storeName(mission.getStore().getName())
                .storeCategory(mission.getStore().getCategory())
                .build();
    }
    public static MissionResDTO.MissionList toGetMissions(List<Mission> missionList) {

        List<MissionResDTO.MissionDTO> missionDTOList = missionList.stream()
                .map(MissionConverter::toGetMissionDTO)
                .collect(Collectors.toList());

        return MissionResDTO.MissionList.builder()
                .missions(missionDTOList)
                .build();
    }
}
