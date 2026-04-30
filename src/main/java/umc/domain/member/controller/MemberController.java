package umc.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.member.dto.MemberReqDTO;
import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.exception.code.MemberSuccessCode;
import umc.global.apiPayload.ApiResponse;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    @PostMapping("/signup")
    public ApiResponse<MemberResDTO.SignUpDTO> signUp(
            @RequestBody MemberReqDTO.SignUpDTO reqDto
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.CREATED, null);
    }

    @GetMapping("/home")
    public ApiResponse<MemberResDTO.HomeViewDTO> getHome(
            @RequestParam String regionName
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.HOME_VIEW, null);
    }
}
