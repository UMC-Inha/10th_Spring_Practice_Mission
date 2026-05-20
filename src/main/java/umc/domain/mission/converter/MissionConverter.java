package umc.domain.mission.converter;

import umc.domain.mission.dto.MissionReqDTO;
import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.entity.Mission;
import umc.domain.store.entity.Store;

public class MissionConverter {
    public static Mission toMission(
            Store store,
            MissionReqDTO.CreateMission dto
    ){
        return Mission.builder()
                .store(store)
                .conditional(dto.conditional())
                .reward_point(dto.reward_point())
                .start_dt(dto.start_dt())
                .end_dt(dto.end_dt())
                .build();
    }

    // 가게 내 미션 조회
    public static MissionResDTO.GetMission toGetMission(
           Mission mission
    ){
        return MissionResDTO.GetMission.builder()
                .missionId(mission.getId())
                .conditional(mission.getConditional())
                .reward_point(mission.getReward_point())
                .start_dt(mission.getStart_dt())
                .end_dt(mission.getEnd_dt())
                .build();

    }

}
