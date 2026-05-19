package umc.domain.review.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.review.dto.ReviewReqDTO;
import umc.domain.review.dto.ReviewResDTO;
import umc.domain.review.entity.Review;
import umc.domain.review.exception.code.ReviewSuccessCode;
import umc.domain.review.service.ReviewService;
import umc.global.apiPayload.ApiResponse;
import umc.global.dto.CusorResDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    //리뷰 작성
    @PostMapping("/v1/reviews/{storeId}/{memberId}")
    public ApiResponse<ReviewResDTO.CreateReviewRes> createReview(
            @PathVariable @NotNull Long memberId,
            @PathVariable @NotNull Long storeId,
            @RequestBody @Valid ReviewReqDTO.CreateReviewReq req
    ){
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_CREATED, reviewService.createReview(memberId, storeId, req));
    }

    @GetMapping("/v1/reviews")
    public ApiResponse<CusorResDTO.Pagination<ReviewResDTO.ReviewRes>> getMyReviews(
            @RequestBody @Valid ReviewReqDTO.MyReview req
    ){
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_FOUND, reviewService.getMyReviews(req));
    }

}
