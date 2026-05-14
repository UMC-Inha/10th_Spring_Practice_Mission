package umc.domain.member.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    CREATED(HttpStatus.CREATED, "MEMBER201_1", "회원가입을 완료했습니다!"),
    HOME_VIEW(HttpStatus.OK, "HOME200_1", "홈 화면"),
    OK(HttpStatus.OK, "MEMBER200_2", "성공적으로 유저를 조회했습니다."),
    MY_PAGE_VIEW(HttpStatus.OK, "MEMBER200_2", "마이페이지를 성공적으로 조회했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
