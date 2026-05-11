package umc.member.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.apiPayload.code.BaseSuccessCode;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK, "MEMBER200_1", "성공적으로 유저를 조회했습니다."),

    JOIN_OK(HttpStatus.OK, "MEMBER200_2", "성공적으로 가입을 완료했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
