package umc.review.exception;

import umc.apiPayload.code.BaseErrorCode;
import umc.apiPayload.exception.ProjectException;

public class ReviewException extends ProjectException {
    public ReviewException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
