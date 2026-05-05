package umc.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.member.dto.MemberReqDTO;
import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.exception.code.MemberSuccessCode;
import umc.domain.member.service.MemberService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.BaseSuccessCode;
import umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {
    private final MemberService memberService;

    //마이 페이지
    @PostMapping("/v1/members/me/{memberId}")
    public ApiResponse<MemberResDTO.MyPageResDTO> getInfo(
            @RequestBody MemberReqDTO.MyPageReqDTO dto
    ){
        BaseSuccessCode code = MemberSuccessCode.MEMBER_SUCCESS;
        return ApiResponse.onSuccess(code, memberService.getInfo(dto));
    }

    //보유 포인트 조회
    @GetMapping("/v1/members/me/points/{memberId}")
    public ApiResponse<MemberResDTO.PointResDTO> getPoint(
            @RequestParam(name = "memberId") Long id
    ){
        return ApiResponse.onSuccess(GeneralSuccessCode.OK, memberService.getPoint(id));
    }

    //회원 가입
    @GetMapping("/v1/members/signup")
    public ApiResponse<MemberResDTO.SignUpRes> signup(){
        return ApiResponse.onSuccess(MemberSuccessCode.CREATED, null);
    }
}
