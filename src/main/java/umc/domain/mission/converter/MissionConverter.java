package umc.domain.mission.converter;

import umc.domain.mission.dto.MissionReqDTO;
import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.entity.Mission;
import umc.domain.store.entity.Store;

public class MissionConverter {

    // 미션 생성 (ReqDTO → Entity)
    public static Mission toPutMission(
            Store store,
            MissionReqDTO.CreateMissionDTO dto
    ){
        return Mission.builder()
                .store(store)
                .conditional(dto.conditional())
                .reward_point(dto.reward_point())
                .start_dt(dto.start_dt())
                .end_dt(dto.end_dt())
                .build();
    }

    // 미션 생성 조회 (Entity → ResDTO)
    public static MissionResDTO.GetCreateMissionDTO toGetCreateMission(Mission mission) {
        return new MissionResDTO.GetCreateMissionDTO(
                mission.getId(),
                mission.getCreatedAt()
        );
    }

    // 미션 조회 (Entity → ResDTO)
    public static MissionResDTO.GetMissionDTO toGetMission(Mission mission) {
        return MissionResDTO.GetMissionDTO.builder()
                .missionId(mission.getId())
                .conditional(mission.getConditional())
                .reward_point(mission.getReward_point())
                .start_dt(mission.getStart_dt())
                .end_dt(mission.getEnd_dt())
                .build();
    }
}