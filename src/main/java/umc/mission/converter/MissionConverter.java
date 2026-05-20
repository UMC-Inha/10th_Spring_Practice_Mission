package umc.mission.converter;

import org.springframework.data.domain.Page;
import umc.apiPayload.dto.BaseResDTO;
import umc.mission.dto.MissionReqDTO;
import umc.mission.dto.MissionResDTO;
import umc.mission.entity.Mapping.MemberMission;
import umc.mission.entity.Mission;
import umc.store.entity.Store;

import java.util.List;
import java.util.stream.Collectors;

public class MissionConverter {

    public static MissionResDTO.MissionInfoDTO toMissionInfoDTO(MemberMission memberMission){
        Mission mission = memberMission.getMission();

        return MissionResDTO.MissionInfoDTO.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .missionContent(mission.getContent())
                .missionPoint(mission.getPoint())
                .missionDeadline(mission.getDeadline())
                .build();
    }

    public static MissionResDTO.MissionListDTO toMissionListDTO(Page<MemberMission> missionPage) {

        // 1. 엔티티 리스트를 DTO 리스트로 변환
        List<MissionResDTO.MissionInfoDTO> missionInfoDTOList = missionPage.getContent().stream()
                .map(MissionConverter::toMissionInfoDTO)
                .collect(Collectors.toList());

        // 2. MissionListDTO 생성 및 페이징 정보 설정
        return MissionResDTO.MissionListDTO.builder()
                .content(missionInfoDTOList)
                .pageable(MissionResDTO.PageInfoDTO.builder()
                        .pageNumber(missionPage.getNumber())
                        .pageSize(missionPage.getSize())
                        .build())
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .hasNext(missionPage.hasNext())
                .build();
    }

    public static MissionResDTO.MissionListDTO toMissionListDTOFromMission(Page<Mission> missionPage) {
        List<MissionResDTO.MissionInfoDTO> missionInfoDTOList = missionPage.getContent().stream()
                .map(mission -> MissionResDTO.MissionInfoDTO.builder()
                        .missionId(mission.getId())
                        .storeName(mission.getStore().getName())
                        .missionContent(mission.getContent())
                        .missionPoint(mission.getPoint())
                        .missionDeadline(mission.getDeadline())
                        .build())
                .collect(Collectors.toList());

        return MissionResDTO.MissionListDTO.builder()
                .content(missionInfoDTOList)
                .pageable(MissionResDTO.PageInfoDTO.builder()
                        .pageNumber(missionPage.getNumber())
                        .pageSize(missionPage.getSize())
                        .build())
                .isFirst(missionPage.isFirst())
                .isLast(missionPage.isLast())
                .hasNext(missionPage.hasNext())
                .build();
    }

    public static Mission toMission(
            Store store,
            MissionReqDTO.CreateMission dto
    ){
        return Mission.builder()
                .store(store)
                .content(dto.content())
                .point(dto.point())
                .deadline(dto.deadline())
                .build();
    }

    public static MissionResDTO.GetMission toGetMission(
            Mission mission
    ){
        return MissionResDTO.GetMission.builder()
                .content(mission.getContent())
                .point(mission.getPoint())
                .missionId(mission.getId())
                .build();
    }

    public static <T> BaseResDTO.Pagination<T> toPagination(
            List<T> data,
            Integer pageNumber,
            Integer pageSize
    ){
        return BaseResDTO.Pagination.<T>builder()
                .data(data)
                .pageNumber(pageNumber)
                .pageSize(pageSize)
                .build();
    }

    // 멤버 미션에서 MissionInfoDto 바꾸기 객체그래프
    public static MissionResDTO.MissionInfoDTO MemberMissionToMissionInfoDTO(MemberMission mm){
        Mission mission = mm.getMission();

        return MissionResDTO.MissionInfoDTO.builder()
                .missionId(mission.getId())
                .storeName(mission.getStore().getName())
                .missionContent(mission.getContent())
                .missionPoint(mission.getPoint())
                .missionDeadline(mission.getDeadline())
                .build();
    }
}
