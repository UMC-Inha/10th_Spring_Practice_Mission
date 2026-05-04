package umc.domain.home.dto;

import lombok.Builder;
import umc.domain.mission.dto.MissionResDTO;

import java.util.Date;
import java.util.List;

public class HomeResDTO {

    //홈 화면 조회
    @Builder
    public record HomeDTO(
            MissionDashBoardDTO missionDashBoard,
            int currentPoint,
            List<MissionResDTO.Mission> unstartedMissionList
    ) {
        @Builder
        public record MissionDashBoardDTO(
                String regionName,
                int missionCount,
                int missionSuccessCount
        ){}
    }

}
