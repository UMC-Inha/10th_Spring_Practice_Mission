package umc.domain.mission.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseSuccessCode;

@AllArgsConstructor
@Getter
public enum MissionSuccessCode implements BaseSuccessCode {

    MISSIONS_VIEW(HttpStatus.OK, "MISSION200_1", "미션 목록 조회가 완료되었습니다."),
    MISSION_STATUS_UPDATED(HttpStatus.OK, "MISSION200_2", "미션 상태가 업데이트 되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

}
