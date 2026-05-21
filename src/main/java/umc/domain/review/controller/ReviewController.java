package umc.domain.review.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.domain.review.dto.ReviewReqDTO;
import umc.domain.review.dto.ReviewResDTO;
import umc.domain.review.exception.code.ReviewSuccessCode;
import umc.domain.review.service.ReviewService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.BaseSuccessCode;

@Validated
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/stores/{storeId}/reviews")
    public ApiResponse<ReviewResDTO.CreateReview> createReview(
            @PathVariable Long storeId,
            @RequestBody @Valid ReviewReqDTO.CreateReview request
            ){
        Long memberId = 1L; // TODO: take memberId from access token(?)
        BaseSuccessCode code = ReviewSuccessCode.REVIEW_WRITE;
        ReviewResDTO.CreateReview response = reviewService.createReview(storeId, memberId, request);
        return ApiResponse.onSuccess(code, response);
    }

    @GetMapping("/reviews")
    public ApiResponse<ReviewResDTO.PaginationCursor<ReviewResDTO.GetMyReview>> getMyReviews(
            @RequestParam @Positive Integer pageSize,
            @RequestParam(defaultValue = "-1") String cursor,
            @RequestParam String query
    ) {
        Long memberId = 1L; // TODO: Get memberId from accessToken
        BaseSuccessCode code = ReviewSuccessCode.VIEW_REVIEW;
        return ApiResponse.onSuccess(code, reviewService.getMyReviews(memberId, pageSize, cursor, query ));
    }
}
