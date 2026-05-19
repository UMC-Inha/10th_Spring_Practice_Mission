package umc.domain.home.controller;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.domain.home.dto.HomeResDTO;
import umc.domain.home.service.HomeService;
import umc.domain.member.exception.code.MemberSuccessCode;
import umc.global.apiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HomeController {

    private final HomeService homeService;

    //홈 화면
    @GetMapping("/v1/home/{memberId}")
    public ApiResponse<HomeResDTO.HomeDTO> getHome(
            @RequestParam(name = "region") @NotBlank @Validated String region,
            @PathVariable @NotNull @Validated Long memberId
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.HOME_VIEW_SUCCESS, homeService.getHome(memberId, region));
    }

}
