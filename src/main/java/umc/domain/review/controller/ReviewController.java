package umc.domain.review.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.review.dto.ReviewRequestDTO;
import umc.domain.review.dto.ReviewResponseDTO;
import umc.domain.review.exception.code.ReviewSuccessCode;
import umc.domain.review.service.ReviewService;
import umc.global.apiPayload.ApiResponse;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    // 특정 가게에 리뷰 작성
    @PostMapping("/stores/{storeId}/reviews")
    public ApiResponse<ReviewResponseDTO.Create> create(
            @PathVariable Long storeId,
            @Valid @RequestBody ReviewRequestDTO.Create dto
    ) {
        Long memberId = 1L;  // 추후 JWT 토큰 사용 시 하드코딩 제거
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_CREATED,
                reviewService.createReview(storeId, memberId, dto)
        );
    }

    // 특정 리뷰에 답글 작성
    @PostMapping("/reviews/{reviewId}/replies")
    public ApiResponse<ReviewResponseDTO.CreateReply> createReply(
            @PathVariable Long reviewId,
            @RequestBody ReviewRequestDTO.CreateReply dto
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_REPLY_CREATED,
                reviewService.createReply(reviewId, dto)
        );
    }

    // 내가 작성한 리뷰 목록 조회
    @GetMapping("/members/me/reviews")
    public ApiResponse<ReviewResponseDTO.CursorPage<ReviewResponseDTO.ReviewDTO>> getMyReviews(
            @RequestParam(defaultValue = "id") String query,
            @RequestParam(required = false) String cursor,
            @RequestParam(defaultValue = "20") Integer pageSize
    ) {
        Long memberId = 1L; // 추후 JWT 토큰 사용 시 하드코딩 제거
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_LIST_OK,
                reviewService.getMyReviews(memberId, query, cursor, pageSize)
        );
    }

}
