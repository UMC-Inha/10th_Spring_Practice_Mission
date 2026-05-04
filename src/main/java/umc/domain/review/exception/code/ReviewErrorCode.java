package umc.domain.review.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    REVIEW_CREATE_FAILED(
            HttpStatus.BAD_REQUEST,
            "REVIEW400_1",
            "리뷰 생성에 실패했습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;

}
