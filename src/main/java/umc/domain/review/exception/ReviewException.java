package umc.domain.review.exception;

import umc.global.apiPayload.code.BaseErrorCode;
import umc.global.apiPayload.exception.ProjectException;

public class ReviewException extends ProjectException {
	public ReviewException(BaseErrorCode errorCode) {
		super(errorCode);
	}
}
