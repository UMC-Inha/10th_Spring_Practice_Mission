package umc.domain.review.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@RequiredArgsConstructor
public enum ReviewErrorCode {

    REVIEW_CREATE_FAILED(
            HttpStatus.BAD_REQUEST,
            "REVIEW400_1",
            "리뷰 생성에 실패했습니다."
    );

    private final HttpStatus status;
    private final String code;
    private final String message;

}
