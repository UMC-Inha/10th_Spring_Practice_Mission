package umc.domain.mission.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    MISSION_LIST_OK(HttpStatus.OK, "MISSION200_1", "미션 목록 조회에 성공했습니다."),
    CREATED(HttpStatus.CREATED, "MISSION201_1", "성공적으로 미션을 생성했습니다."),
    OK(HttpStatus.OK, "MISSION200_2", "성공적으로 미션을 조회했습니다."),
    ;


    private final HttpStatus status;
    private final String code;
    private final String message;
}
