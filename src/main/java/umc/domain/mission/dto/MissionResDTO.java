package umc.domain.mission.dto;

import lombok.Builder;
import lombok.Getter;

import java.util.Date;
import java.util.List;

public class MissionResDTO {

    @Builder
    public record MissionList(
            List<Mission> missions
    ){}

    @Builder
    public record Mission(
            Long missionId,
            int condition,
            int missionPoint,
            Date dueDate,
            Long storeId,
            String storeName,
            String storeCategory
    ){}

}
