package umc.domain.store.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.store.enums.ReviewSuccessCode;
import umc.domain.store.enums.StoreSuccessCode;
import umc.domain.store.dto.StoreReqDTO;
import umc.domain.store.dto.StoreResDTO;
import umc.domain.store.service.StoreService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.BaseSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/v1/stores/{storeId}")
    public ApiResponse<StoreResDTO.GetStoreInfo> getStoreInfo(
            @RequestBody StoreReqDTO.GetStoreInfo dto
    ){
        BaseSuccessCode code = StoreSuccessCode.OK;
        return ApiResponse.onSuccess(code, storeService.getStoreInfo(dto));
    }

    @PostMapping("/v1/stores/{storeId}/reviews")
    public ApiResponse<StoreResDTO.GetReviewInfo> getReviewInfo(
            @RequestBody StoreReqDTO.GetReviewInfo dto
    ){
        BaseSuccessCode code = ReviewSuccessCode.OK;
        return ApiResponse.onSuccess(code, storeService.getReviewInfo(dto));
    }

    @PostMapping("/v1/stores/create")
    public ApiResponse<String> createStore() {
        storeService.createStore();
        return ApiResponse.onSuccess(StoreSuccessCode.OK, "저장 완료");
    }

    @PostMapping("/v1/stores/reviews/create")
    public ApiResponse<String> createReview() {
        storeService.createReview();
        return ApiResponse.onSuccess(ReviewSuccessCode.OK, "저장 완료");
    }


}