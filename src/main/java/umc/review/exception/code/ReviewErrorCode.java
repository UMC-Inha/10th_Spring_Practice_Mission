package umc.review.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.apiPayload.code.BaseErrorCode;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode implements BaseErrorCode {

    REVIEW_NOT_FOUND(HttpStatus.NOT_FOUND,
            "REVIEW404_1",
            "존재하지 않는 리뷰입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
