package umc.mission.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.apiPayload.code.BaseErrorCode;

@Getter
@RequiredArgsConstructor
public enum MissionErrorCode implements BaseErrorCode {
    MISSION_NOT_FOUND(HttpStatus.NOT_FOUND,
            "MISSION404_1",
            "존재하지 않는 미션입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
