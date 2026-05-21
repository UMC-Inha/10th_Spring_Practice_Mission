package umc.domain.mission.converter;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Page;
import umc.domain.member.entity.mapping.MemberMission;
import umc.domain.mission.dto.MissionRequestDTO;
import umc.domain.mission.dto.MissionResponseDTO;
import umc.domain.mission.entity.Mission;
import umc.domain.store.entity.Store;

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

    public static MissionResponseDTO.MissionList toMissionList(Page<MemberMission> memberMissions) {
        List<MissionResponseDTO.MissionItem> missionItems = new ArrayList<>();
        for (MemberMission mm : memberMissions.getContent()) {
            missionItems.add(toMissionItem(mm));
        }
        return MissionResponseDTO.MissionList.builder()
                .missions(missionItems)
                .pageNumber(memberMissions.getNumber())
                .pageSize(memberMissions.getSize())
                .totalElements(memberMissions.getTotalElements())
                .totalPages(memberMissions.getTotalPages())
                .hasNext(memberMissions.hasNext())
                .build();
    }

    public static MissionResponseDTO.HomeMissionItem toHomeMissionItem(Mission mission) {
        long dDay = ChronoUnit.DAYS.between(LocalDate.now(), mission.getDeadLine());
        return MissionResponseDTO.HomeMissionItem.builder()
                .dDay(dDay)
                .missionCondition(mission.getMissionCondition())
                .rewardPoint(mission.getRewardPoint())
                .storeName(mission.getStore().getName())
                .storeCategory(mission.getStore().getFood().getName())
                .build();
    }

    public static MissionResponseDTO.HomeMissionList toHomeMissionList(
            String regionName, Integer point, Integer completedCount, List<Mission> missions) {
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

    // 가게 미션 생성
    public static Mission toMission(
            Store store,
            MissionRequestDTO.CreateMission dto
    ) {
        return Mission.builder()
                .deadLine(dto.deadLine())
                .rewardPoint(dto.rewardPoint())
                .missionCondition(dto.missionCondition())
                .store(store)
                .build();
    }

    // 가게 내 미션 조회
    public static MissionResponseDTO.GetMission toGetMission(
            Mission mission
    ) {
        return MissionResponseDTO.GetMission.builder()
                .deadLine(mission.getDeadLine())
                .rewardPoint(mission.getRewardPoint())
                .missionCondition(mission.getMissionCondition())
                .build();
    }

    public static <T> MissionResponseDTO.Pagination<T> toPagination(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ) {
        return MissionResponseDTO.Pagination.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }
}
