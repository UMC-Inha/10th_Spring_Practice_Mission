package umc.domain.review.exception.code;

import org.springframework.http.HttpStatus;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import umc.global.apiPayload.code.BaseSuccessCode;

@Getter
@RequiredArgsConstructor
public enum ReviewSuccessCode implements BaseSuccessCode {
	REVIEW_CREATED(HttpStatus.CREATED, "REVIEW200_1", "리뷰 작성에 성공했습니다."),
	REVIEW_FOUND(HttpStatus.OK, "REVIEW200_2", "리뷰 목록 조회에 성공했습니다."),
	REVIEW_DELETED(HttpStatus.OK, "REVIEW200_3", "리뷰를 삭제했습니다.");

	private final HttpStatus status;
	private final String code;
	private final String message;
}