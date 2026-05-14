package umc.domain.region.exception.code;

import lombok.AllArgsConstructor;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import umc.global.apiPayload.code.BaseErrorCode;

@Getter
@AllArgsConstructor
public enum RegionErrorCode implements BaseErrorCode {

    NOT_FOUND_BY_NAME(HttpStatus.NOT_FOUND, "REGION404_1", "해당 이름을 가진 지역이 존재하지 않습니다."),
    ;

    private final HttpStatus status;
    private final String code;
    private final String message;

}
