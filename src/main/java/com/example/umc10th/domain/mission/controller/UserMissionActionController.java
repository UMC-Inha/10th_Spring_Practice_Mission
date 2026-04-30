package com.example.umc10th.domain.mission.controller;

import com.example.umc10th.domain.mission.dto.UserMissionVerificationResponseDto;
import com.example.umc10th.domain.mission.enums.UserMissionStatus;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import java.time.LocalDateTime;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserMissionActionController {

	@PostMapping("/api/user-missions/{userMissionId}/verifications")
	public ApiResponse<UserMissionVerificationResponseDto> requestVerification(
		@PathVariable Long userMissionId
	) {
		LocalDateTime now = LocalDateTime.now();
		UserMissionVerificationResponseDto response = new UserMissionVerificationResponseDto(
			userMissionId,
			UserMissionStatus.SUCCESS_REQUESTED,
			1L,
			"920394810",
			now,
			now.plusMinutes(10)
		);

		return ApiResponse.onSuccess(MissionSuccessCode.USER_MISSION_VERIFICATION_CREATED, response);
	}
}
