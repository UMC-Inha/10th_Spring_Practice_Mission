package umc.domain.store.exception.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@RequiredArgsConstructor
public enum StoreSuccessCode implements BaseSuccessCode {
	STORE_FOUND(HttpStatus.OK, "STORE200_1", "가게 조회에 성공했습니다."),
	STORE_REGISTERED(HttpStatus.CREATED, "STORE200_2", "새로운 가게를 등록했습니다."),
	CATEGORY_FOUND(HttpStatus.OK, "STORE200_3", "음식 카테고리 조회에 성공했습니다."),
	REGION_REGISTERED(HttpStatus.CREATED, "STORE200_4", "지역 정보를 등록했습니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}