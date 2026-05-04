package umc.domain.point.exception;

import umc.global.apiPayload.code.BaseErrorCode;
import umc.global.apiPayload.exception.ProjectException;

public class PointException extends ProjectException {
    public PointException(BaseErrorCode code) {

        super(code);
    }
}
