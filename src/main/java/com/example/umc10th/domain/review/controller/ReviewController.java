package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewListResponseDto;
import com.example.umc10th.domain.review.dto.ReviewRequestDto;
import com.example.umc10th.domain.review.dto.ReviewResponseDto;
import com.example.umc10th.domain.review.enums.ReviewSortType;
import com.example.umc10th.domain.review.exception.code.ReviewSuccessCode;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Positive;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReviewController {

	private final ReviewService reviewService;

	public ReviewController(ReviewService reviewService) {
		this.reviewService = reviewService;
	}

	@PostMapping("/api/member-missions/{memberMissionId}/review")
	public ApiResponse<ReviewResponseDto> createReview(
		@PathVariable
		@Positive(message = "회원 미션 ID는 양수여야 합니다.")
		Long memberMissionId,
		@Valid @RequestBody ReviewRequestDto request
	) {
		ReviewResponseDto response = reviewService.createReview(memberMissionId, request);

		return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_CREATED, response);
	}

	@GetMapping("/api/reviews/my")
	public ApiResponse<ReviewListResponseDto> getMyReviews(
		@RequestParam
		@Positive(message = "회원 ID는 양수여야 합니다.")
		Long memberId,
		@RequestParam(defaultValue = "ID") ReviewSortType sort,
		@RequestParam(required = false) String cursor,
		@RequestParam(defaultValue = "10")
		@Min(value = 1, message = "조회 크기는 1 이상이어야 합니다.")
		@Max(value = 50, message = "조회 크기는 최대 50까지 가능합니다.")
		Integer size
	) {
		ReviewListResponseDto response = reviewService.getMyReviews(memberId, sort, cursor, size);

		return ApiResponse.onSuccess(ReviewSuccessCode.MY_REVIEW_LIST_FOUND, response);
	}
}
