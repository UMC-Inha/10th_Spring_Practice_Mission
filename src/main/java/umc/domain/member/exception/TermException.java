package umc.domain.member.exception;

import umc.global.apiPayload.code.BaseErrorCode;
import umc.global.apiPayload.exception.ProjectException;

public class TermException extends ProjectException {
	public TermException(BaseErrorCode errorCode) {
		super(errorCode);
	}
}
