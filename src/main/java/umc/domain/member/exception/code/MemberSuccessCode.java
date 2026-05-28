package umc.domain.member.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

    MEMBER_CREATED(HttpStatus.CREATED, "MEMBER200_1", "성공적으로 유저를 생성했습니다."),
    MEMBER_OK(HttpStatus.OK, "MEMBER200_2", "성공적으로 유저를 조회했습니다."),

    LOGIN_SUCCESS(HttpStatus.OK, "MEMBER200_3", "성공적으로 로그인했습니다."),  // 추가!

    MISSION_CREATED(HttpStatus.CREATED, "MEMBERMISSION200_1", "성공적으로 멤버 미션을 생성했습니다."),
    MEMBER_MISSION_OK(HttpStatus.OK, "MEMBERMISSION200_2", "성공적으로 멤버미션을 조회했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}