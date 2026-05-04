package umc.domain.home.exception;

import umc.global.apiPayload.code.BaseErrorCode;
import umc.global.apiPayload.exception.ProjectException;

public class HomeException extends ProjectException {
    public HomeException(BaseErrorCode code) {
        super(code);
    }
}
