package umc.domain.store.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@RequiredArgsConstructor

public enum StoreSuccessCode implements BaseSuccessCode {
    OK(HttpStatus.OK, "COMMON200_1", "성공적으로 요청을 처리했습니다.");  // ← 세미콜론!

    private final HttpStatus status;
    private final String code;
    private final String message;
}
