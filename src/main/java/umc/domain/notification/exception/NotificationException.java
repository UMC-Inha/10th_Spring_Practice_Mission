package umc.domain.notification.exception;

import umc.global.apiPayload.code.BaseErrorCode;
import umc.global.apiPayload.exception.ProjectException;

public class NotificationException extends ProjectException {
    public NotificationException(BaseErrorCode code) {

        super(code);
    }
}
