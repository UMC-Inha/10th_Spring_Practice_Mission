package umc.domain.inquiry.exception;

import umc.global.apiPayload.code.BaseErrorCode;
import umc.global.apiPayload.exception.ProjectException;

public class InquiryException extends ProjectException {
    public InquiryException(BaseErrorCode code) {

        super(code);
    }
}
