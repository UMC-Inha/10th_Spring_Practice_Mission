package umc.domain.mission.converter;

import org.springframework.data.domain.Page;
import umc.domain.mission.dto.HomeResDTO;
import umc.domain.mission.entity.Mission;

import java.util.List;
import java.util.stream.Collectors;

public class HomeConverter {

    public static HomeResDTO.RegionMissionDTO toRegionMissionDTO(Mission mission) {
        return new HomeResDTO.RegionMissionDTO(
                mission.getId(),
                mission.getStore().getName(),
                "카테고리", // 예: mission.getStore().getCategory().getName()
                7, // D-Day 계산 로직
                mission.getContent(), // 💡 missionContent
                mission.getMissionPoint()
        );
    }

    public static HomeResDTO.RegionMissionListDTO toRegionMissionListDTO(Page<Mission> missionPage) {
        List<HomeResDTO.RegionMissionDTO> dtoList = missionPage.stream()
                .map(HomeConverter::toRegionMissionDTO)
                .collect(Collectors.toList());

        Long nextCursor = missionPage.hasNext() ? (long) (missionPage.getNumber() + 1) : null;

        return new HomeResDTO.RegionMissionListDTO(dtoList, nextCursor, missionPage.hasNext());
    }
}
