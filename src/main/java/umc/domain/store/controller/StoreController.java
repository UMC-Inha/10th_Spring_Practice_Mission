package umc.domain.store.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.store.dto.StoreReqDTO;
import umc.domain.store.dto.StoreResDTO;
import umc.domain.store.exception.code.StoreSuccessCode;
import umc.domain.store.service.StoreService;
import umc.global.apiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class StoreController {

    private final StoreService storeService;

    // 가게 조회
    @GetMapping("/{storeId}")
    public ApiResponse<StoreResDTO.GetStoreInfoDTO> getStoreInfo(
            @PathVariable Long storeId
    ){
        return ApiResponse.onSuccess(StoreSuccessCode.STORE_SUCCESS_CODE,
                storeService.getStoreInfo(storeId));
    }

    // 리뷰 조회
    @GetMapping("/{storeId}/reviews")
    public ApiResponse<StoreResDTO.GetReviewInfoDTO> getReviewInfo(
            @PathVariable Long storeId,
            @RequestParam Long memberId
    ){
        return ApiResponse.onSuccess(StoreSuccessCode.REVIEW_SUCCESS_CODE,
                storeService.getReviewInfo(memberId, storeId));
    }

    // 가게 생성
    @PostMapping
    public ApiResponse<StoreResDTO.GetCreateStoreDTO> createStore(
            @RequestBody @Valid StoreReqDTO.CreateStoreDTO dto
    ){
        return ApiResponse.onSuccess(StoreSuccessCode.STORE_CREATED,
                storeService.createStore(dto));
    }

    // 리뷰 생성
    @PostMapping("/{storeId}/reviews")
    public ApiResponse<StoreResDTO.GetCreateReviewDTO> createReview(
            @PathVariable Long storeId,
            @RequestParam Long memberId,
            @RequestBody @Valid StoreReqDTO.CreateReviewDTO dto
    ){
        return ApiResponse.onSuccess(StoreSuccessCode.REVIEW_CREATED,
                storeService.createReview(memberId, storeId, dto));
    }
}