package umc.review.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.apiPayload.code.BaseSuccessCode;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK, "Review200_1", "성공적으로 리뷰를 생성했습니다."),
    GET_OK(HttpStatus.OK, "Review200_2", "성공적으로 리뷰를 조회했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
