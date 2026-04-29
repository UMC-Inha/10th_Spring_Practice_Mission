package umc.domain.mission.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseSuccessCode;
@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
            "COMMON200",
            "성공적으로 미션을 조회했습니다."),
    MISSION_OK(HttpStatus.OK,
            "COMMON200",
            "미션 완료 처리에 성공했습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
