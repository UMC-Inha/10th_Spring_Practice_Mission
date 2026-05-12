package umc.domain.store.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@RequiredArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {


    STORE_SUCCESS(HttpStatus.OK,
            "STORE200_1",
            "성공적으로 식당을 조회했습니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;

}