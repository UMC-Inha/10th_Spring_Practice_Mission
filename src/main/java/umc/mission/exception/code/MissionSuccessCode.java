package umc.mission.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.apiPayload.code.BaseErrorCode;
import umc.apiPayload.code.BaseSuccessCode;

@Getter
@RequiredArgsConstructor
public enum MissionSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK, "MISSION200_1", "성공적으로 미션을 조회했습니다."),
    COMPLETE_OK(HttpStatus.OK, "MISSION200_2", "미션 성공 신청 완료."),
    CREATED(HttpStatus.OK, "MISSION200_3", "성공적으로 미션 생성 완료."),
    HOME_OK(HttpStatus.OK, "HOME200", "홈 화면이 성공적으로 조회되었습니다."),
    USER_MISSION_OK(HttpStatus.OK, "USER_MISSION200", "내 미션이 성공적으로 조회되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
