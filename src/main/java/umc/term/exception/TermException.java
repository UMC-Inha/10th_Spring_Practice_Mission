package umc.term.exception;

import umc.apiPayload.code.BaseErrorCode;
import umc.apiPayload.exception.ProjectException;

public class TermException extends ProjectException {
    public TermException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
