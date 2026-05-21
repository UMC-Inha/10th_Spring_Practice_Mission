package umc.domain.auth.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements BaseErrorCode {

    DUPLICATED_EMAIL(HttpStatus.NOT_FOUND, "AUTH400_1", "이미 가입되어있는 이메일입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
