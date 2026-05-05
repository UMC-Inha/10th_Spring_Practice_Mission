package com.example.umc10th.domain.mission.dto;

import com.example.umc10th.domain.mission.enums.MemberMissionStatus;
import java.time.LocalDateTime;

public record MemberMissionVerificationResponseDto(
	Long memberMissionId,
	MemberMissionStatus status,
	Long verificationId,
	String verificationCode,
	LocalDateTime successRequestedAt,
	LocalDateTime expiresAt
) {
}
