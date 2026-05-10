package umc.domain.member.converter;

import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.entity.Member;
import umc.domain.mission.entity.Mission;
import umc.domain.region.entity.Region;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

public class MemberConverter {

    public static MemberResDTO.MyPageViewDTO toMyPageViewDTO(Member member) {
        return MemberResDTO.MyPageViewDTO.builder()
                .id(member.getId())
                .profileUrl(member.getProfileUrl())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .points(member.getPoints())
                .build();
    }

    public static MemberResDTO.HomeViewDTO toHomeViewDTO(
            Member member,
            Region region,
            Integer completedCount,
            Integer goalCount,
            List<Mission> results,
            boolean hasNextPage
    ) {
        MemberResDTO.HomeViewDTO.TotalInformationDto totalInfo =
                MemberResDTO.HomeViewDTO.TotalInformationDto.builder()
                        .memberId(member.getId())
                        .regionId(region.getId())
                        .regionName(region.getRegionName())
                        .missionCount(completedCount)
                        .missionGoalCount(goalCount)
                        .build();

        Mission lastMission = results.isEmpty() ? null : results.get(results.size() - 1);

        List<MemberResDTO.HomeViewDTO.MissionDto> missionDtos = results.stream()
                .map(m -> MemberResDTO.HomeViewDTO.MissionDto.builder()
                        .missionId(m.getId())
                        .missionDescription(m.getDescription())
                        .missionPoints(m.getPoints())
                        .dDay((int) ChronoUnit.DAYS.between(LocalDate.now(), m.getDueDate()))
                        .storeId(m.getStore().getId())
                        .storeName(m.getStore().getName())
                        .storeType(m.getStore().getStoreType().getDescription())
                        .build())
                .toList();

        return MemberResDTO.HomeViewDTO.builder()
                .totalInformation(totalInfo)
                .missionList(missionDtos)
                .nextCursorId(lastMission.getId())
                .nextCursorDueDate(lastMission.getDueDate())
                .hasNext(hasNextPage)
                .build();
    }
}
