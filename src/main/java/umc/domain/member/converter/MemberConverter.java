package umc.domain.member.converter;

import org.springframework.data.domain.Page;
import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.entity.Member;
import umc.domain.mission.entity.Mission;
import umc.domain.store.entity.Region;
import umc.domain.store.entity.Store;

import java.util.List;
import java.util.Map;

public class MemberConverter {

    public static MemberResDTO.GetInfo toGetInfo(Member member){
        return MemberResDTO.GetInfo.builder()
                .name(member.getName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .point(member.getCurrentPoint().intValue())
                .build();
    }

    public static MemberResDTO.HomeMission toHomeMission(
            Mission mission,
            Map<Long, String> storeCategoryMap
    )
    {
        Store store = mission.getStore();

        return MemberResDTO.HomeMission.builder()
                .missionId(mission.getId())
                .storeId(store.getId())
                .storeName(store.getName())
                .storeCategory(storeCategoryMap.get(store.getId()))
                .missionContent(mission.getContent())
                .rewardPoint(mission.getRewardPoint())
                .dueDate(mission.getEnd_at())
                .build();
    }

    public static MemberResDTO.Home toHome(
            Member member,
            Region region,
            Page<Mission> missions,
            int completedMissionCount,
            Map<Long, String> storeCategoryMap
    ){
        List<MemberResDTO.HomeMission> homeMissions = missions.getContent()
                .stream()
                .map(mission -> MemberConverter.toHomeMission(mission, storeCategoryMap))
                .toList();

        return MemberResDTO.Home.builder()
                .regionId(region.getId())
                .regionName(region.getName())
                .currentPoint(member.getCurrentPoint())
                .completedMissionCount(completedMissionCount)
                .missions(homeMissions)
                .build();
    }
}
