package umc.domain.mission.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;
import umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    MISSION_LIST_GET_SUCCESS(
            HttpStatus.OK,
            "MISSION200_1",
            "미션 목록 조회가 성공적으로 완료되었습니다."
    ),

    MISSION_UPDATE(
            HttpStatus.OK,
            "MISSION200_2",
            "미션 상태 업데이트가 완료되었습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}
