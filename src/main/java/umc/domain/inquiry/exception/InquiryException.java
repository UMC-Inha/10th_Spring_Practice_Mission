package umc.domain.inquiry.exception;

import umc.global.apiPayload.code.BaseErrorCode;
import umc.global.apiPayload.exception.GeneralException;

public class InquiryException extends GeneralException {
    public InquiryException(BaseErrorCode code) {
        super(code);
    }
}
