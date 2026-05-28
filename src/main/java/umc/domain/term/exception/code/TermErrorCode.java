package umc.domain.term.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum TermErrorCode implements BaseErrorCode {

    REQUIRED_TERM_NOT_AGREED(HttpStatus.BAD_REQUEST,
            "TERM400_1",
            "필수 약관에 동의해야 합니다."),
    TERM_MASTER_DATA_NOT_FOUND(HttpStatus.NOT_FOUND,
            "TERM404_1",
            "약관 정보를 찾을 수 없습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
