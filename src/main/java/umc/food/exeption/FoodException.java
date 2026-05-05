package umc.food.exeption;

import umc.apiPayload.code.BaseErrorCode;
import umc.apiPayload.exception.ProjectException;

public class FoodException extends ProjectException {
    public FoodException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
