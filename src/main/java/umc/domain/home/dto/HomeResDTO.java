package umc.domain.home.dto;

import lombok.Builder;

import java.util.Date;
import java.util.List;

public class HomeResDTO {

    //홈 화면 조회
    @Builder
    public record HomeDTO(
            MissionDashBoardDTO missionDashBoard,
            int currentPoint,
            List<MissionDTO> unstartedMissionList
    ) {
        @Builder
        public record MissionDashBoardDTO(
                String regionName,
                int missionCount,
                int missionSuccessCount
        ){}
    }

    @Builder
    public record MissionDTO(
            Long missionId,
            int condition,
            int missionPoint,
            Date dueDate,
            Long storeId,
            String storeName,
            String storeCategory
    ){}

}
