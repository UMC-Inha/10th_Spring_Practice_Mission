package umc.domain.mission.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;

@Getter
@RequiredArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {

    QUERY_NOT_VALID(HttpStatus.BAD_REQUEST,
            "MISSION400_1",
            "쿼리가 유효하지 않습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
