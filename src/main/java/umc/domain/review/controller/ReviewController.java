package umc.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.review.dto.ReviewReqDTO;
import umc.domain.review.dto.ReviewResDTO;
import umc.domain.review.exception.code.ReviewSuccessCode;
import umc.domain.review.service.ReviewService;
import umc.global.apiPayload.ApiResponse;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping("/{storeId}")
    public ApiResponse<ReviewResDTO.ReviewCreateDTO> createReview(
            @PathVariable Long storeId,
            @RequestBody ReviewReqDTO.ReviewCreateDTO reqDto
    ) {
        ReviewResDTO.ReviewCreateDTO resDto = reviewService.createReview(1L, storeId, reqDto);
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_CREATED, resDto);
    }

    @GetMapping
    public ApiResponse<ReviewResDTO.CursorPage> getMyReviews(
            @RequestParam(defaultValue = "id") String query,  // 커서 종류
            @RequestParam(required = false) String cursor,    // 커서
            @RequestParam(defaultValue = "20") Integer pageSize
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_LIST_VIEW, reviewService.getMyReviews(
                1L,
                query,
                cursor,
                pageSize
        ));
    }
}
