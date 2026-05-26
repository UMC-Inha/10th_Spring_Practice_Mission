package umc.domain.member.exception.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import umc.global.apiPayload.code.BaseErrorCode;

@Getter
@RequiredArgsConstructor
public enum TermErrorCode implements BaseErrorCode {
	TERM_NOT_FOUND(HttpStatus.NOT_FOUND, "TERM404_1", "해당 약관을 찾을 수 없습니다."),

	REQUIRED_TERM_NOT_AGREED(HttpStatus.BAD_REQUEST, "TERM400_1", "필수 약관에 동의해야 합니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}
