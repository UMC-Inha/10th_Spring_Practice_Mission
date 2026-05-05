package umc.location.exception;

import umc.apiPayload.code.BaseErrorCode;
import umc.apiPayload.exception.ProjectException;

public class LocationException extends ProjectException {
    public LocationException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
