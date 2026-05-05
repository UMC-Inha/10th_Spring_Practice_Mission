package umc.domain.mission.exception.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import umc.global.apiPayload.code.BaseErrorCode;

@Getter
@RequiredArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {
	MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MISSION404_1", "해당 미션이 존재하지 않습니다."),
	ALREADY_CHALLENGING(HttpStatus.BAD_REQUEST, "MISSION400_1", "이미 도전 중인 미션입니다."),
	MISSION_NOT_CHALLENGING(HttpStatus.BAD_REQUEST, "MISSION400_2", "도전 중인 미션이 아닙니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}