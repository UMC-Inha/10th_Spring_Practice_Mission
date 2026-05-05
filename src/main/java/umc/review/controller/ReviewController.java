package umc.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.apiPayload.ApiResponse;
import umc.review.dto.ReviewReqDTO;
import umc.review.dto.ReviewResDTO;
import umc.review.exception.code.ReviewSuccessCode;
import umc.review.service.ReviewService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/v1/stores/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReviewDTO> createReview(
            @PathVariable(name = "storeId") Long storeId,
            @RequestBody ReviewReqDTO.ReviewInfoDTO request
    ) {
        ReviewResDTO.CreateReviewDTO result = reviewService.createReview(storeId, request);

        return ApiResponse.onSuccess(ReviewSuccessCode.OK, result);
    }
}
