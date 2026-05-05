package umc.domain.member.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    CREATED(HttpStatus.CREATED, "MEMBER201_1", "회원 가입이 완료되었습니다."),
    HOME_VIEW(HttpStatus.OK, "MEMBER200_1", "홈 화면이 성공적으로 조회되었습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

}
