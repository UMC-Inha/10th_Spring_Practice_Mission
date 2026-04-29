package umc.Inquiry.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.apiPayload.code.BaseErrorCode;

@Getter
@RequiredArgsConstructor
public enum InquiryErrorCode implements BaseErrorCode {
    INQUIRY_ERROR_CODE(HttpStatus.NOT_FOUND,
            "INQUIRY404_1",
            "존재하지 않는 문의입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
