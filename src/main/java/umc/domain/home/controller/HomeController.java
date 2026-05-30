package umc.domain.home.controller;

import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.domain.home.dto.HomeResDTO;
import umc.domain.home.service.HomeService;
import umc.domain.member.exception.code.MemberSuccessCode;
import umc.global.apiPayload.ApiResponse;
import umc.global.security.entity.AuthMember;

@RestController
@Validated
@RequiredArgsConstructor
@RequestMapping("/api")
public class HomeController {

    private final HomeService homeService;

    //홈 화면
    @GetMapping("/v1/home")
    public ApiResponse<HomeResDTO.HomeDTO> getHome(
            @RequestParam(name = "region") @NotBlank String region,
            @AuthenticationPrincipal AuthMember member
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.HOME_VIEW_SUCCESS, homeService.getHome(member, region));
    }

}
