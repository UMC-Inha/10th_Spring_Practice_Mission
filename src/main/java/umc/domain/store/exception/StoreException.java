package umc.domain.store.exception;

import umc.global.apiPayload.code.BaseErrorCode;
import umc.global.apiPayload.exception.ProjectException;

public class StoreException extends ProjectException {
    public StoreException(BaseErrorCode code) {

        super(code);
    }
}
