package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.MemberMissionVerificationResponseDto;
import com.example.umc10th.domain.mission.enums.MemberMissionStatus;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberMissionActionController {

	@PostMapping("/api/member-missions/{memberMissionId}/verifications")
	public ApiResponse<MemberMissionVerificationResponseDto> requestVerification(
		@PathVariable Long memberMissionId
	) {
		LocalDateTime now = LocalDateTime.now();
		MemberMissionVerificationResponseDto response = new MemberMissionVerificationResponseDto(
			memberMissionId,
			MemberMissionStatus.SUCCESS_REQUESTED,
			1L,
			"920394810",
			now,
			now.plusMinutes(10)
		);

		return ApiResponse.onSuccess(MissionSuccessCode.MEMBER_MISSION_VERIFICATION_CREATED, response);
	}
}
