package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.MemberMissionStatus;
import java.time.LocalDateTime;
import java.util.List;

public record MemberMissionListResponseDto(
	MemberMissionStatus status,
	List<MemberMissionSummaryDto> missions,
	Integer size,
	Long nextCursor,
	Boolean hasNext
) {
	public record MemberMissionSummaryDto(
		Long memberMissionId,
		Long missionId,
		Long storeId,
		String storeName,
		String missionContent,
		Integer missionPoint,
		MemberMissionStatus status,
		LocalDateTime challengedAt,
		LocalDateTime successRequestedAt,
		LocalDateTime completedAt,
		Integer daysRemaining
	) {
	}
}
