package umc.domain.member.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;

@Getter
@RequiredArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {
    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_1", "해당 사용자를 찾을 수 없습니다."),

    MEMBER_MISSION_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBERMISSION404_1", "해당 미션을 찾을 수 없습니다."),

    DUPLICATE_EMAIL(HttpStatus.CONFLICT, "MEMBER409_1", "이미 사용 중인 이메일입니다."),

    INVALID_PASSWORD(HttpStatus.UNAUTHORIZED, "MEMBER401_1", "비밀번호가 일치하지 않습니다."),

    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}