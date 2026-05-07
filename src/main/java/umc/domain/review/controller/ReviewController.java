package umc.domain.review.controller;

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
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping(value = "/stores/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReview> createReview(
            @PathVariable Long storeId,
            @RequestParam Long memberId,
            @ModelAttribute ReviewReqDTO.CreateReview request
            ){
        BaseSuccessCode code = ReviewSuccessCode.REVIEW_WRITE;
        ReviewResDTO.CreateReview response = reviewService.createReview(storeId, memberId, request);
        return ApiResponse.onSuccess(code, response);
    }
}
