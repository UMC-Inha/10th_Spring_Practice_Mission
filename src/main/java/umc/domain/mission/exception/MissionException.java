package umc.domain.mission.exception;

import umc.global.apiPayload.code.BaseErrorCode;
import umc.global.apiPayload.exception.ProjectException;

public class MissionException extends ProjectException {
	public MissionException(BaseErrorCode errorCode) {
		super(errorCode);
	}
}
