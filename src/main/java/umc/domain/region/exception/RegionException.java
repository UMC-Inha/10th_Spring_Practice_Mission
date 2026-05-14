package umc.domain.region.exception;

import umc.global.apiPayload.code.BaseErrorCode;
import umc.global.apiPayload.exception.ProjectException;

public class RegionException extends ProjectException {
    public RegionException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
