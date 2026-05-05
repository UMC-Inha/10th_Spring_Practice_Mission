package umc.domain.store.controller;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import umc.domain.review.dto.ReviewReqDTO;
import umc.domain.review.dto.ReviewResDTO;
import umc.domain.store.dto.StoreReqDTO;
import umc.domain.store.service.StoreService;
import umc.global.apiPayload.ApiResponse;

@RestController
@RequestMapping("/api/stores")
@RequiredArgsConstructor
public class StoreController {
	private final StoreService storeService;

	// 리뷰작성
	@PostMapping("/{storeId}/reviews")
	public ApiResponse<ReviewResDTO.CreateReviewResultDTO> createReview(
		@PathVariable(name = "storeId") Long storeId,
		@RequestBody ReviewReqDTO.ReviewCreateDTO request) {

		return ApiResponse.onSuccess();
	}
}
