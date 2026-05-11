package umc.location.exception.code;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import umc.apiPayload.code.BaseErrorCode;

@Getter
@RequiredArgsConstructor
public enum LocationErrorCode implements BaseErrorCode {
    LOCATION_ERROR_CODE(HttpStatus.NOT_FOUND,"LOCATION404_1", "존재하지 않는 지역입니다."),
    LOCATION_LABEL_ERROR_CODE(HttpStatus.NOT_FOUND,"LOCATION404_2", "해당하는 지역 라벨이 없습니다.");


    private final HttpStatus status;
    private final String code;
    private final String message;
}
