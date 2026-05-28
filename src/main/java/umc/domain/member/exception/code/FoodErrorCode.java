package umc.domain.member.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;

@Getter
@RequiredArgsConstructor
public enum FoodErrorCode implements BaseErrorCode {

    INVALID_FOOD_ID(HttpStatus.BAD_REQUEST,
            "FOOD400_1",
            "유효하지 않은 음식 ID가 포함되어 있습니다");

    private final HttpStatus status;
    private final String code;
    private final String message;

    }
