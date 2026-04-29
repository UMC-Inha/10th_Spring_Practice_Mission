package umc.domain.review.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.review.dto.ReviewReqDTO;
import umc.domain.review.dto.ReviewResDTO;
import umc.domain.review.exception.code.ReviewSuccessCode;
import umc.domain.review.service.ReviewService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.BaseSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{storeId}/reviews")
    @Operation(summary = "가게 리뷰 작성")
    public ApiResponse<ReviewResDTO.CreateReviewResultDTO> createReview(
            @PathVariable(name = "storeId") Long StroeId,
            @RequestBody ReviewReqDTO.ReviewDTO request
    ){
        BaseSuccessCode code = ReviewSuccessCode.OK;
        ReviewResDTO.CreateReviewResultDTO result = reviewService.createReview(storeId, request);
        return ApiResponse.onSuccess(code, result);
    }
}
