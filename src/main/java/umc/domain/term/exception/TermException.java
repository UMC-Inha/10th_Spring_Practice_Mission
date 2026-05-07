package umc.domain.term.exception;

import umc.global.apiPayload.code.BaseErrorCode;
import umc.global.apiPayload.exception.ProjectException;

public class TermException extends ProjectException {
    public TermException(BaseErrorCode code) {

        super(code);
    }
}
