package umc.domain.mission.converter;

import umc.domain.member.entity.mapping.MemberMission;
import umc.domain.mission.dto.MissionResponseDTO;
import umc.domain.mission.entity.Mission;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

public class MissionConverter {

    public static MissionResponseDTO.MissionItem toMissionItem(MemberMission memberMission) {
        return MissionResponseDTO.MissionItem.builder()
                .storeId(memberMission.getMission().getStore().getId())
                .storeName(memberMission.getMission().getStore().getName())
                .missionId(memberMission.getMission().getId())
                .missionCondition(memberMission.getMission().getMissionCondition())
                .rewardPoint(memberMission.getMission().getRewardPoint())
                .build();
    }

    public static MissionResponseDTO.MissionList toMissionList(List<MemberMission> memberMissions) {
        List<MissionResponseDTO.MissionItem> missionItems = new ArrayList<>();
        for (MemberMission mm : memberMissions) {
            missionItems.add(toMissionItem(mm));
        }
        return MissionResponseDTO.MissionList.builder()
                .mission(missionItems)
                .build();
    }

    public static MissionResponseDTO.HomeMissionItem toHomeMissionItem(Mission mission) {
        long dDay = ChronoUnit.DAYS.between(LocalDate.now(), mission.getDeadLine());
        return MissionResponseDTO.HomeMissionItem.builder()
                .dDay(dDay)
                .missionCondition(mission.getMissionCondition())
                .rewardPoint(mission.getRewardPoint())
                .storeName(mission.getStore().getName())
                .storeCategory(mission.getStore().getCategory().getName())
                .build();
    }

    public static MissionResponseDTO.HomeMissionList toHomeMissionList(
            String regionName, Integer point, Long completedCount, List<Mission> missions) {
        List<MissionResponseDTO.HomeMissionItem> items = new ArrayList<>();
        for (Mission m : missions) {
            items.add(toHomeMissionItem(m));
        }
        return MissionResponseDTO.HomeMissionList.builder()
                .region(regionName)
                .point(point)
                .missionCompletedCount(completedCount)
                .missionGoalCount(10)
                .missionPoint(1000)
                .mission(items)
                .build();
    }
}
