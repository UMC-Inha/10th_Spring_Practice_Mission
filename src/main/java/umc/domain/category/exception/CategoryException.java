package umc.domain.category.exception;

import umc.global.apiPayload.code.BaseErrorCode;
import umc.global.apiPayload.exception.ProjectException;

public class CategoryException extends ProjectException {
    public CategoryException(BaseErrorCode code) {
        super(code);
    }
}
