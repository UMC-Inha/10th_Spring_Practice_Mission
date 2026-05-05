package umc.review.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.apiPayload.code.BaseSuccessCode;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {

    OK(HttpStatus.OK,
            "MEMBER200_1",
            "성공적으로 리뷰를 생성했습니다."),;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
