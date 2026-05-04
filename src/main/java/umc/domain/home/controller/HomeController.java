package umc.domain.home.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.home.dto.HomeResDTO;
import umc.domain.member.exception.code.MemberSuccessCode;
import umc.global.apiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HomeController {

    //홈 화면
    @GetMapping("/v1/home")
    public ApiResponse<HomeResDTO.HomeDTO> getHome(
            @RequestParam String region
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.HOME_VIEW_SUCCESS, null);
    }

}
