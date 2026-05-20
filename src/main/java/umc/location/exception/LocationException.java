package umc.location.exception;

import umc.global.apiPayload.code.BaseErrorCode;
import umc.global.apiPayload.exception.ProjectException;

public class LocationException extends ProjectException {
    public LocationException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
