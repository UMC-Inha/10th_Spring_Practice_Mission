package umc.mission.converter;

import umc.mission.dto.MissionResDTO;
import umc.mission.entity.Mission;

public class MissionConverter {

    public static MissionResDTO.MissionInfoDTO toMissionInfoDTO(Mission mission){
        return MissionResDTO.MissionInfoDTO.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .missionContent(mission.getContent())
                .missionPoint(mission.getPoint())
                .missionDeadline(mission.getDeadline())
                .build();
    }


}
