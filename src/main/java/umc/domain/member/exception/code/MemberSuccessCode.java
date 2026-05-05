package umc.domain.member.exception.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@RequiredArgsConstructor
public enum MemberSuccessCode implements BaseSuccessCode {

	OK(HttpStatus.OK, "MEMBER200_1", "성공적으로 유저를 조회했습니다."),
	MEMBER_JOINED(HttpStatus.CREATED, "MEMBER200_2", "회원가입에 성공했습니다."),
	MEMBER_LOGIN_SUCCESS(HttpStatus.OK, "MEMBER200_3", "로그인에 성공했습니다."),
	MEMBER_DELETE_SUCCESS(HttpStatus.OK, "MEMBER200_4", "회원 탈퇴가 완료되었습니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;

}
