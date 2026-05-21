package umc.domain.member.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum MemberErrorCode implements BaseErrorCode {

    MEMBER_NOT_FOUND(HttpStatus.NOT_FOUND, "MEMBER404_1", "해당 사용자를 찾을 수 없습니다."),
    EMAIL_ALREADY_EXIST(HttpStatus.CONFLICT, "MEMBER409_1", "해당 이메일은 이미 사용 중 입니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
