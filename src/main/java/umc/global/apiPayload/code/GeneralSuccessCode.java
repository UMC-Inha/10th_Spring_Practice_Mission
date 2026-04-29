package umc.global.apiPayload.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum GeneralSuccessCode {

	OK(HttpStatus.OK,
		"COMMON201_1",
		"성공적으로 요청을 처리했습니다."),
	;

	private final HttpStatus status;
	private final String code;
	private final String message;
}
