package umc.member.exception;

import umc.apiPayload.code.BaseErrorCode;
import umc.apiPayload.exception.ProjectException;

public class MemberException extends ProjectException {
    public MemberException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
