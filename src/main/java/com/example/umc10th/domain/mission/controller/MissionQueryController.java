package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MemberMissionListResponseDto;
import com.example.umc10th.domain.mission.enums.MemberMissionStatus;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import java.time.LocalDateTime;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MissionQueryController {

	@GetMapping("/api/member-missions")
	public ApiResponse<MemberMissionListResponseDto> getMemberMissions(
		@RequestParam(required = false) Long regionId,
		@RequestParam MemberMissionStatus status,
		@RequestParam(required = false) Long cursor,
		@RequestParam Integer size
	) {
		LocalDateTime now = LocalDateTime.now();
		MemberMissionListResponseDto response = new MemberMissionListResponseDto(
			status,
			List.of(
				new MemberMissionListResponseDto.MemberMissionSummaryDto(
					44L,
					101L,
					regionId == null ? 1L : regionId,
					"춘리마라탕",
					"10,000원 이상의 식사 시",
					500,
					status,
					now,
					status == MemberMissionStatus.SUCCESS_REQUESTED ? now : null,
					status == MemberMissionStatus.COMPLETED ? now : null,
					7
				)
			),
			size,
			cursor == null ? null : cursor - 1,
			false
		);

		return ApiResponse.onSuccess(MissionSuccessCode.MEMBER_MISSION_LIST_FOUND, response);
	}
}
