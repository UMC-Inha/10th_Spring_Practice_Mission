package umc.store.exception;

import umc.apiPayload.code.BaseErrorCode;
import umc.apiPayload.exception.ProjectException;

public class StoreException extends ProjectException {
    public StoreException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
