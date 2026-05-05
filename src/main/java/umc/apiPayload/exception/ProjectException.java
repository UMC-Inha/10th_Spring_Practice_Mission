package umc.apiPayload.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import umc.apiPayload.code.BaseErrorCode;

@Getter
@RequiredArgsConstructor
public class ProjectException extends RuntimeException {
    private final BaseErrorCode errorCode;
}
