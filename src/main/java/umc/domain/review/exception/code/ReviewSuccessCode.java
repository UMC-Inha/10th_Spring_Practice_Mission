package umc.domain.review.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@AllArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    REVIEW_CREATED(HttpStatus.CREATED, "REVIEW201_1", "리뷰 작성이 완료되었습니다."),
    REVIEW_LIST_VIEW(HttpStatus.OK, "REVIEW200_1", "성공적으로 리뷰 목록을 조회했습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

}
