package umc.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.review.dto.ReviewReqDTO;
import umc.domain.review.dto.ReviewResDTO;
import umc.domain.review.entity.Review;
import umc.domain.review.exception.code.ReviewSuccessCode;
import umc.domain.review.service.ReviewService;
import umc.global.apiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    //리뷰 작성
    @PostMapping("/v1/reviews/{storeId}/{memberId}")
    public ApiResponse<ReviewResDTO.ReviewRes> createReview(
            @PathVariable Long memberId,
            @PathVariable Long storeId,
            @RequestBody ReviewReqDTO.ReviewReq req
    ){
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_CREATED, reviewService.createReview(memberId, storeId, req));
    }

}
