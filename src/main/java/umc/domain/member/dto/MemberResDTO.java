package umc.domain.member.dto;

import lombok.Builder;

import java.time.LocalDate;
import java.util.List;

public class MemberResDTO {

    @Builder
    public record GetInfo(
            String name,
            String profileUrl,
            String email,
            String phoneNumber,
            Integer point
    ){}

    @Builder
    public record Home(
            Long regionId,
            String regionName,
            Long currentPoint,
            Integer completedMissionCount,
            List<HomeMission> missions
    ){}

    @Builder
    public record HomeMission(
            Long missionId,
            Long storeId,
            String storeName,
            String storeCategory,
            String missionContent,
            Long rewardPoint,
            LocalDate dueDate
    ){}

    @Builder
    public record CreateMember(
            Long memberId
    ) {}
}
