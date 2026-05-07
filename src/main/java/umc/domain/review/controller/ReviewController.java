package umc.domain.review.controller;

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

    @PostMapping("/stores/{storeId}/reviews")
    public ApiResponse<ReviewResponseDTO.Create> create (
        @PathVariable Long storeId,
        @RequestBody ReviewRequestDTO.Create dto
    ) {
        Long memberId = 1L;
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_CREATED,
                reviewService.createReview(storeId, memberId, dto)
        );
    }

}
