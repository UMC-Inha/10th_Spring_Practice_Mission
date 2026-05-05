package umc.domain.inquiry.exception.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@RequiredArgsConstructor
public enum InquirySuccessCode implements BaseSuccessCode {
	INQUIRY_CREATED(HttpStatus.CREATED, "INQUIRY200_1", "문의 사항이 접수되었습니다."),
	INQUIRY_FOUND(HttpStatus.OK, "INQUIRY200_2", "문의 내역 조회에 성공했습니다."),
	INQUIRY_DELETED(HttpStatus.OK, "INQUIRY200_3", "문의 내용을 삭제했습니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}