package umc.domain.store.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@RequiredArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {

    STORE_CREATED(HttpStatus.CREATED, "STORE200_1", "성공적으로 가게를 생성했습니다."),
    STORE_SUCCESS_CODE(HttpStatus.OK, "STORE200_2", "성공적으로 가게를 조회했습니다."),

    REVIEW_CREATED(HttpStatus.CREATED, "REVIEW200_1", "성공적으로 리뷰를 생성했습니다."),
    REVIEW_SUCCESS_CODE(HttpStatus.OK, "REVIEW200_2", "성공적으로 리뷰를 조회했습니다."),

    ;

    private final HttpStatus status;
    private final String code;
    private final String message;
}
