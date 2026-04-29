package umc.domain.mission.exception.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {
	MISSION_FOUND(HttpStatus.OK, "MISSION200_1", "미션 목록 조회에 성공했습니다."),
	MISSION_CHALLENGE_STARTED(HttpStatus.CREATED, "MISSION200_2", "미션 도전을 시작했습니다."),
	MISSION_COMPLETED(HttpStatus.OK, "MISSION200_3", "미션을 성공적으로 완료했습니다."),
	STORE_MISSION_CREATED(HttpStatus.CREATED, "MISSION200_4", "가게에 새로운 미션을 추가했습니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}