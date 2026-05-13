package umc.domain.review.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    NOT_APPLY_CURSOR(HttpStatus.BAD_REQUEST, "REVIEW400_1", "지원하지 않는 커서 종류입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

}
