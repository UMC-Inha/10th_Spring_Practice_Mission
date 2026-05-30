package umc.member.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_1", "해당 사용자를 찾을 수 없습니다."),
    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "MEMBER409_1", "중복된 이메일입니다."),
    INVALID_GENDER_TYPE(HttpStatus.BAD_REQUEST, "GENDER400_1", "유효하지 않은 성별입니다."),
    INVALID_SOCIAL_TYPE(HttpStatus.BAD_REQUEST, "SOCIAL400_1", "유효하지 않은 소셜 타입입니다."),
    INVALID_PASSWORD(HttpStatus.BAD_REQUEST, "MEMBER400_1", "일치하지 않는 비밀번호입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
