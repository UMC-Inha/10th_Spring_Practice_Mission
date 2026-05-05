package umc.Inquiry.exception;

import umc.apiPayload.code.BaseErrorCode;
import umc.apiPayload.exception.ProjectException;

public class InquiryException extends ProjectException {
    public InquiryException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
