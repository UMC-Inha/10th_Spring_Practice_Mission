package umc.domain.home.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@RequiredArgsConstructor
public enum HomeSuccessCode implements BaseSuccessCode {

    HOME_VIEW_SUCCESS(HttpStatus.OK,
            "MEMBER200_1",
            "홈 화면 조회가 완료되었습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
