package umc.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.member.dto.MemberReqDTO;
import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.exception.code.MemberSuccessCode;
import umc.domain.member.service.MemberService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.BaseSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/member")
public class MemberController {
    private final MemberService memberService;

    //마이페이지
    @PostMapping("/v1/me")
    public ApiResponse<MemberResDTO.getInfo> getInfo(
            @RequestBody MemberReqDTO.getInfo dto
    ){
        BaseSuccessCode code = MemberSuccessCode.MEMBER_SUCCESS;
        return ApiResponse.onSuccess(code, memberService.getInfo(dto));
    }

}
