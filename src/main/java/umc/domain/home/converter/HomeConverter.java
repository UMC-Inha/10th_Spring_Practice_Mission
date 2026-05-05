package umc.domain.home.converter;

import umc.domain.home.dto.HomeResDTO;
import umc.domain.mission.converter.MissionConverter;
import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.entity.Mission;

import java.util.ArrayList;
import java.util.List;

public class HomeConverter {

    public static HomeResDTO.HomeDTO toGetHome(String region, Integer missionCount, Integer missionSuccessCount, Integer currentPoint, List<Mission> unstartedMissionList) {
        List<MissionResDTO.MissionDTO> missionDTOList = unstartedMissionList.stream()
                .map(MissionConverter::toGetMissionDTO)
                .toList();

        HomeResDTO.HomeDTO.MissionDashBoardDTO dashBoardDTO =
                HomeResDTO.HomeDTO.MissionDashBoardDTO.builder()
                        .regionName(region)
                        .missionCount(missionCount)
                        .missionSuccessCount(missionSuccessCount)
                        .build();

        return HomeResDTO.HomeDTO.builder()
                .currentPoint(currentPoint)
                .missionDashBoard(dashBoardDTO)
                .unstartedMissionList(missionDTOList)
                .build();
    }
}
