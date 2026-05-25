package umc.domain.auth.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
public enum AuthSuccessCode implements BaseSuccessCode {

    // 200
    LOGIN_SUCCESS(HttpStatus.OK, "AUTH200_1", "로그인이 성공적으로 완료되었습니다."),

    // 201
    SIGN_UP(HttpStatus.CREATED, "AUTH201_1", "회원가입이 완료되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
