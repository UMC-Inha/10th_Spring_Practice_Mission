package com.example.umc10th.domain.review.controller;

import com.example.umc10th.domain.review.dto.ReviewReqDto;
import com.example.umc10th.domain.review.dto.ReviewResDto;
import com.example.umc10th.domain.review.service.ReviewService;
import com.example.umc10th.global.apiPayload.ApiResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores/{storeId}/reviews")
public class ReviewController {

    private final ReviewService reviewService;

    @GetMapping
    public ApiResponse<ReviewResDto> getStoreReviews(
            @PathVariable Long storeId,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size
    ) {
        ReviewResDto response = reviewService.getStoreReviews(storeId, page, size);
        return ApiResponse.onSuccess(response);
    }

    @PostMapping("/{reviewId}/reply")
    public ApiResponse<Void> writeReviewReply(
            @PathVariable Long storeId,
            @PathVariable Long reviewId,
            @Valid @RequestBody ReviewReqDto request
    ) {
        reviewService.writeReviewReply(storeId, reviewId, request);
        return ApiResponse.onSuccess(null);
    }
}