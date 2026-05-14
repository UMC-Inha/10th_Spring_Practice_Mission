package umc.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.member.dto.MemberReqDTO;
import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.exception.code.MemberSuccessCode;
import umc.domain.member.service.MemberService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.BaseSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/users/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(){
        Long memberId = 1L; // TODO: memberId from access token
        BaseSuccessCode code = MemberSuccessCode.MEMBER_VIEW;
        MemberResDTO.GetInfo response = memberService.getInfo(memberId);
        return ApiResponse.onSuccess(code, response);
    }

    @GetMapping("/home")
    public ApiResponse<MemberResDTO.Home> getHome(
            @RequestParam Long regionId,
            @RequestParam(defaultValue = "0") Integer page
    ){
        Long memberId = 1L; // TODO: memberId from access token
        BaseSuccessCode code = MemberSuccessCode.MEMBER_VIEW;
        MemberResDTO.Home response = memberService.getHome(memberId, regionId, page);
        return ApiResponse.onSuccess(code, response);
    }

    @PostMapping("/users")
    public ApiResponse<MemberResDTO.CreateMember> createMember(
            @RequestBody MemberReqDTO.CreateMember request
    ) {
        BaseSuccessCode code = MemberSuccessCode.MEMBER_CREATED;
        return ApiResponse.onSuccess(code, null);
    }
}
