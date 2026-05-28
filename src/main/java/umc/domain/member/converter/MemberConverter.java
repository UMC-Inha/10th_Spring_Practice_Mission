package umc.domain.member.converter;

import org.springframework.data.domain.Page;
import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.entity.Member;
import umc.domain.member.enums.Gender;
import umc.domain.member.exception.code.MemberErrorCode;
import umc.domain.mission.entity.Mission;
import umc.domain.store.entity.Region;
import umc.domain.store.entity.Store;
import umc.global.security.dto.OAuthDTO;

import java.time.LocalDate;
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
                .dueDate(mission.getEndAt())
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

    public static MemberResDTO.CreateMember toCreateMember(
            Member member
    ) {
        return MemberResDTO.CreateMember.builder()
                .memberId(member.getId())
                .build();
    }

    public static MemberResDTO.Login toLogin(String accessToken){
        return MemberResDTO.Login.builder()
                .accessToken(accessToken)
                .build();
    }

    // nullable = false 거나 NullPointerException이 발생 가능한 attribute에는 더미
    public static Member toMember(OAuthDTO dto) {
        return Member.builder()
                .name(dto.getName())
                .email(dto.getSocialEmail())
                .password("")
                .gender(Gender.NONE)
                .birth(LocalDate.of(2000, 1, 1))
                .address("NONE")
                .socialProvider(dto.getSocialProvider())
                .socialId(dto.getSocialId())
                .currentPoint(0L)
                .build();
    }
}
