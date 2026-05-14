package umc.domain.mission.converter;

import org.springframework.data.domain.Page;
import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.entity.Mission;
import umc.domain.mission.entity.mapping.MemberMission;
import umc.domain.store.entity.Store;

import java.util.List;

public class MissionConverter {

    public static MissionResDTO.MissionPreview toGetMission(MemberMission memberMission){
        Mission mission = memberMission.getMission();
        Store store = mission.getStore();

        return MissionResDTO.MissionPreview.builder()
                .memberMissionId(memberMission.getId())
                .storeId(store.getId())
                .storeName(store.getName())
                .missionContent(mission.getContent())
                .rewardPoint(mission.getRewardPoint())
                .status(memberMission.getStatus())
                .dueDate(mission.getEnd_at())
                .build();
    }

    public static MissionResDTO.GetMissions toGetMissions(Page<MemberMission> memberMissions){
        List<MissionResDTO.MissionPreview> missions = memberMissions.getContent()
                .stream()
                .map(MissionConverter::toGetMission)
                .toList();

        return MissionResDTO.GetMissions.builder()
                .missions(missions)
                .build();
    }
}
