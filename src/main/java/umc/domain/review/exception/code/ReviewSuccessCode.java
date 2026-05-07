package umc.domain.review.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    REVIEW_WRITE(HttpStatus.OK,
            "REVIEW200_1",
            "리뷰를 성공적으로 작성했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
