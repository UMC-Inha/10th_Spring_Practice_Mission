package umc.domain.inquiry.exception.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import umc.global.apiPayload.code.BaseErrorCode;

@Getter
@RequiredArgsConstructor
public enum InquiryErrorCode implements BaseErrorCode {
	INQUIRY_NOT_FOUND(HttpStatus.NOT_FOUND, "INQUIRY404_1", "문의 내역을 찾을 수 없습니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}