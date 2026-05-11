package umc.food.exeption.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.apiPayload.code.BaseErrorCode;

@Getter
@RequiredArgsConstructor
public enum FoodErrorCode implements BaseErrorCode {

    FOOD_NOT_FOUND(HttpStatus.NOT_FOUND,
            "FOOD404_1",
            "존재하지 않는 음식입니다.");

    private final HttpStatus status;
    private final String code;
    private final String message;
}
