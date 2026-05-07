package umc.domain.store.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.store.dto.StoreReqDTO;
import umc.domain.store.dto.StoreResDTO;
import umc.domain.store.enums.StoreSuccessCode;
import umc.domain.store.service.StoreService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.BaseSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class StoreController {

    private final StoreService storeService;

    @PostMapping("/v1/stores/{storeId}/reviews")
    public ApiResponse<StoreResDTO.GetInfo> getInfo(
            @RequestBody StoreReqDTO.GetInfo dto
    ){
        BaseSuccessCode code = StoreSuccessCode.OK;
        return ApiResponse.onSuccess(code, storeService.getInfo(dto));
    }
}