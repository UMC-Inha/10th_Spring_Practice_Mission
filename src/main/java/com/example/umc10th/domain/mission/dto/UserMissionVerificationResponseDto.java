package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.UserMissionStatus;
import java.time.LocalDateTime;

public record UserMissionVerificationResponseDto(
	Long userMissionId,
	UserMissionStatus status,
	Long verificationId,
	String verificationCode,
	LocalDateTime successRequestedAt,
	LocalDateTime expiresAt
) {
}
