package umc.domain.auth.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum AuthErrorCode implements BaseErrorCode {

    // 400
    REQUIRED_TERM_NOT_AGREED(HttpStatus.BAD_REQUEST, "AUTH400_1", "필수 약관은 동의해야 합니다."),
    INVALID_TERM(HttpStatus.BAD_REQUEST, "AUTH400_2", "유효하지 않은 약관입니다."),
    INVALID_FOOD(HttpStatus.BAD_REQUEST, "AUTH400_3", "유효하지 않은 음식입니다."),
    TERMS_MISMATCH(HttpStatus.BAD_REQUEST, "AUTH400_4", "모든 약관에 대한 동의 여부가 필요합니다."),
    NOT_SUPPORT_SOCIAL_PROVIDER(HttpStatus.BAD_REQUEST, "AUTH400_5", "지원하지 않는 소셜 로그인입니다."),

    // 401
    OAUTH_MISSING_ATTRIBUTES(HttpStatus.UNAUTHORIZED, "AUTH401_1", "소셜 로그인 필수 정보가 누락되었습니다."),
    INVALID_LOGIN_FORM(HttpStatus.UNAUTHORIZED, "AUTH401_2", "아이디나 비밀번호가 틀렸습니다."),

    // 409
    DUPLICATED_EMAIL(HttpStatus.CONFLICT, "AUTH409_1", "이미 가입되어있는 이메일입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
