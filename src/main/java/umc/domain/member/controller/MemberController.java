package umc.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.member.dto.MemberRequestDTO;
import umc.domain.member.dto.MemberResponseDTO;
import umc.domain.member.exception.code.MemberSuccessCode;
import umc.domain.member.service.MemberService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.BaseSuccessCode;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/v1/users/me")
    public ApiResponse<MemberResponseDTO.GetInfo> getInfo(
            @RequestBody MemberRequestDTO.GetInfo dto
    ) {
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getInfo(dto));
    }

}
