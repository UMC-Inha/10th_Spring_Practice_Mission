package umc.review.controller;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.global.apiPayload.ApiResponse;
import umc.global.dto.BaseResDTO;
import umc.review.dto.ReviewReqDTO;
import umc.review.dto.ReviewResDTO;
import umc.review.exception.code.ReviewSuccessCode;
import umc.review.service.ReviewService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {
    private final ReviewService reviewService;

    @PostMapping("/v1/stores/{storeId}/members/{memberId}/reviews")
    @Operation(summary = "리뷰 작성")
    public ApiResponse<ReviewResDTO.CreateReviewDTO> createReview(
            @PathVariable(name = "storeId") Long storeId,
            @PathVariable(name = "memberId") Long memberId,
            @Valid @RequestBody ReviewReqDTO.ReviewInfoDTO request
    ) {
        ReviewResDTO.CreateReviewDTO result = reviewService.createReview(storeId, memberId, request);

        return ApiResponse.onSuccess(ReviewSuccessCode.OK, result);
    }

    @GetMapping("/v1/members/{memberId}/reviews")
    @Operation(summary = "멤버의 리뷰 조회")
    public ApiResponse<BaseResDTO.SliceResponse<ReviewResDTO.ReviewDetailDTO>> getMyReviews(
            @PathVariable Long memberId,
            @RequestParam Integer pageSize,
            @RequestParam String cursor,
            @RequestParam String query
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.GET_OK, reviewService.getMyReviews(memberId, pageSize, cursor, query));
    }
}
