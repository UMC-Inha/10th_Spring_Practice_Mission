package umc.domain.mission.converter;

import org.springframework.data.domain.Page;
import umc.domain.mission.dto.MissionReqDTO;
import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.entity.Mission;
import umc.domain.mission.entity.mapping.MemberMission;
import umc.domain.store.entity.Store;

import java.time.LocalDate;
import java.util.List;

public class MissionConverter {

    public static MissionResDTO.MissionPreview toGetMemberMission(MemberMission memberMission){
        Mission mission = memberMission.getMission();
        Store store = mission.getStore();

        return MissionResDTO.MissionPreview.builder()
                .memberMissionId(memberMission.getId())
                .storeId(store.getId())
                .storeName(store.getName())
                .missionContent(mission.getContent())
                .rewardPoint(mission.getRewardPoint())
                .status(memberMission.getStatus())
                .dueDate(mission.getEndAt())
                .build();
    }

    public static MissionResDTO.GetMissions toGetMemberMissions(Page<MemberMission> memberMissions){
        List<MissionResDTO.MissionPreview> missions = memberMissions.getContent()
                .stream()
                .map(MissionConverter::toGetMemberMission)
                .toList();

        return MissionResDTO.GetMissions.builder()
                .missions(missions)
                .build();
    }

    public static Mission toMission(
            Store store,
            MissionReqDTO.CreateMission dto
    ){
        return Mission.builder()
                .store(store)
                .content(dto.conditional())
                .rewardPoint(Long.valueOf(dto.point()))
                .endAt(dto.deadLine())
                .title(dto.title())
                .startedAt(LocalDate.now())
                .build();
    }

    public static MissionResDTO.GetMission toGetMission(
            Mission mission
    ) {
        return MissionResDTO.GetMission.builder()
                .conditional(mission.getContent())
                .point(Math.toIntExact(mission.getRewardPoint()))
                .missionId(mission.getId())
                .build();
    }


    /*
    // 오프셋 페이징
    public static <T> MissionResDTO.Pagination<T> toPagination(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){
        return MissionResDTO.Pagination.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }
     */

    public static <T> MissionResDTO.PaginationOffset<T> toPaginationOffset(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){
        return MissionResDTO.PaginationOffset.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }


    // 커서 페이징
    public static <T> MissionResDTO.Pagination<T> toPagination(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){
        return MissionResDTO.Pagination.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }
}
