package umc.term.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;

@Getter
@RequiredArgsConstructor
public enum TermErrorCode implements BaseErrorCode {
    TERM_NOT_FOUND(HttpStatus.NOT_FOUND, "TERM404_1", "존재하지 않는 약관입니다."),
    REQUIRED_TERM_NOT_AGREE(HttpStatus.BAD_REQUEST, "TERM400_1", "필수 약관에 모두 동의하지 않았습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
