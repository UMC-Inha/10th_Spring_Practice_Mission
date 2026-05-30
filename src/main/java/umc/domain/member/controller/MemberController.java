package umc.domain.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import umc.domain.member.dto.MemberReqDTO;
import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.exception.code.MemberSuccessCode;
import umc.domain.member.service.MemberService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.BaseSuccessCode;
import umc.global.apiPayload.code.GeneralSuccessCode;
import umc.global.security.entity.AuthMember;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {
    private final MemberService memberService;

    //마이 페이지
    @PostMapping("/v1/members/me")
    public ApiResponse<MemberResDTO.MyPageResDTO> getInfo(
            @AuthenticationPrincipal AuthMember member
            ){
        BaseSuccessCode code = MemberSuccessCode.MEMBER_SUCCESS;
        return ApiResponse.onSuccess(code, memberService.getInfo(member));
    }

    //보유 포인트 조회
    @GetMapping("/v1/members/me/points")
    public ApiResponse<MemberResDTO.PointResDTO> getPoint(
            @AuthenticationPrincipal AuthMember member
    ){
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, memberService.getPoint(member));
    }

    //회원 가입
    @PostMapping("/v1/auth/members/signup")
    public ApiResponse<MemberResDTO.SignUpRes> signup(
            @RequestBody @Valid MemberReqDTO.SignUpReq dto
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.CREATED, memberService.signUp(dto));
    }

    //로그인
    @PostMapping("v1/auth/members/login")
    public ApiResponse<MemberResDTO.LoginRes> login(
            @RequestBody @Valid MemberReqDTO.LoginReq dto
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.MEMBER_SUCCESS, memberService.login(dto));
    }
}
