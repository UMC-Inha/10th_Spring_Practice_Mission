package umc.food.exeption;

import umc.global.apiPayload.code.BaseErrorCode;
import umc.global.apiPayload.exception.ProjectException;

public class FoodException extends ProjectException {
    public FoodException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
