package umc.domain.auth.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements BaseErrorCode {

    DUPLICATED_EMAIL(HttpStatus.NOT_FOUND, "AUTH400_1", "이미 가입되어있는 이메일입니다."),
    REQUIRED_TERM_NOT_AGREED(HttpStatus.BAD_REQUEST, "AUTH404_1", "필수 약관은 동의해야 합니다."),
    INVALID_TERM(HttpStatus.BAD_REQUEST, "AUTH404_2", "유효하지 않은 약관입니다."),
    INVALID_FOOD(HttpStatus.BAD_REQUEST, "AUTH404_3", "유효하지 않은 음식입니다."),
    TERMS_MISMATCH(HttpStatus.BAD_REQUEST, "AUTH404_4", "모든 약관에 대한 동의 여부가 필요합니다."),
    INVALID_LOGIN_FORM(HttpStatus.BAD_REQUEST, "AUTH404_5", "아이디나 비밀번호가 틀렸습니다.")
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
