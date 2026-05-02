package umc.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.review.dto.ReviewReqDTO;
import umc.domain.review.exception.code.ReviewSuccessCode;
import umc.global.apiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    //리뷰 작성
    @PostMapping("/v1/missionts/{mission-id}/reviews")
    public ApiResponse<ReviewReqDTO.ReviewReq> createReview(
            @PathVariable Long missionId,
            @RequestBody ReviewReqDTO.ReviewReq req
    ){
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_CREATED, null);
    }

}
