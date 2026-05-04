package umc.domain.member.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    HOME_VIEW_SUCCESS(HttpStatus.OK,
            "MEMBER200_1",
            "홈 화면 조회가 완료되었습니다."),

    MEMBER_SUCCESS(HttpStatus.OK,
            "MEMBER200_2",
            "성공적으로 유저를 조회했습니다."),

    CREATED(HttpStatus.CREATED,
            "MEMBER201_1",
            "회원 가입이 완료되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
