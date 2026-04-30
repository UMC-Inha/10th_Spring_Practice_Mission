package umc.domain.review.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.review.dto.ReviewReqDTO;
import umc.domain.review.dto.ReviewResDTO;
import umc.domain.review.exception.code.ReviewSuccessCode;
import umc.global.apiPayload.ApiResponse;

@RestController
@RequestMapping("/api/reviews")
@RequiredArgsConstructor
public class ReviewController {

    @PostMapping("/{memberMissionId}")
    public ApiResponse<ReviewResDTO.ReviewCreateDTO> createReview(
            @PathVariable Long memberMissionId,
            @ModelAttribute ReviewReqDTO.ReviewCreateDTO reqDto
    ) {
        return ApiResponse.onSuccess(ReviewSuccessCode.REVIEW_CREATED, null);
    }
}
