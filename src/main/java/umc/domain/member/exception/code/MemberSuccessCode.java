package umc.domain.member.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    MEMBER_VIEW(HttpStatus.OK,
            "MEMBER200_1",
            "성공적으로 유저를 조회했습니다."),
    MEMBER_CREATED(HttpStatus.OK,
            "MEMBER200_2",
            "회원을 성공적으로 생성했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
