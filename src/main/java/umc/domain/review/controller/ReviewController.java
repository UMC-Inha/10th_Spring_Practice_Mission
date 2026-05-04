package umc.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.review.dto.ReviewReqDTO;
import umc.domain.review.dto.ReviewResDTO;
import umc.domain.review.exception.code.ReviewSuccessCode;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.BaseSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ReviewController {

    @PostMapping(value = "/stores/{storeId}/reviewes")
    public ApiResponse<ReviewResDTO.CreateReview> createReview(
            @PathVariable Long storeId,
            @ModelAttribute ReviewReqDTO.CreateReview request
            ){
        BaseSuccessCode code = ReviewSuccessCode.REVIEW_WRITE;
        return ApiResponse.onSuccess(code, null);
    }
}
