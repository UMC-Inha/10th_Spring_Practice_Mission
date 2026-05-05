package umc.mission.exception;

import umc.apiPayload.code.BaseErrorCode;
import umc.apiPayload.exception.ProjectException;

public class MissionException extends ProjectException {
    public MissionException(BaseErrorCode errorCode) {
        super(errorCode);
    }
}
