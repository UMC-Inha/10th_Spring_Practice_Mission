package umc.domain.member.dto;

import lombok.Builder;

import java.util.List;

public class MemberResDTO {

    @Builder
    public record SignUpDTO(
            Long memberId,
            String name,
            String birth,
            String address
    ) {}

    @Builder
    public record HomeViewDTO(
            TotalInformationDto totalInformation,
            List<MissionDto> missionList
    ) {
        @Builder
        public record TotalInformationDto(
                Long memberId,
                Long regionId,
                String regionName,
                Integer missionCount,
                Integer missionGoalCount
        ) {}

        @Builder
        public record MissionDto(
                Long missionId,
                String missionDescription,
                Integer missionPoints,
                Integer dDay,
                Long storeId,
                String storeName,
                String storeType
        ) {}
    }
}
