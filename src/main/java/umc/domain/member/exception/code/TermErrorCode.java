package umc.domain.member.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;

@Getter
@RequiredArgsConstructor
public enum TermErrorCode implements BaseErrorCode  {

    INVALID_TERM_ID(
            HttpStatus.BAD_REQUEST,
            "TERM400_1",
            "유효하지 않은 약관 ID가 포함되어 있습니다."),

    REQUIRED_TERM_NOT_AGREED(
            HttpStatus.BAD_REQUEST,
            "TERM400_2",
            "필수 약관에 동의해야 합니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;
}
