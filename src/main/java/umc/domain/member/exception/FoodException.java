package umc.domain.member.exception;

import umc.global.apiPayload.code.BaseErrorCode;
import umc.global.apiPayload.exception.ProjectException;

public class FoodException extends ProjectException {
    public FoodException(BaseErrorCode code) {
        super(code);
    }
}
