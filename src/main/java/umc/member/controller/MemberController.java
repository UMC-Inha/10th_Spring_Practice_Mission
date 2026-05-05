package umc.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.apiPayload.ApiResponse;
import umc.apiPayload.code.BaseSuccessCode;
import umc.member.dto.MemberReqDTO;
import umc.member.dto.MemberResDTO;
import umc.member.entity.Member;
import umc.member.exception.code.MemberSuccessCode;
import umc.member.service.MemberService;
import umc.mission.dto.MissionResDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/v1/users/me")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(
            @RequestBody MemberReqDTO.GetInfo dto
    ){
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getInfo(dto));
    }

    @GetMapping("/v1/home")
    public ApiResponse<MissionResDTO.MissionListDTO> getHomeInfo(
            @RequestParam(name = "regionName") String regionName
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.OK, result);
    }

    @PostMapping("/v1/auth/signup")
    public ApiResponse<MemberResDTO.AuthResDTO.SignUpResultDTO> signUp(
            @RequestBody MemberReqDTO.SingUpDTO request
    ) {
        MemberResDTO.AuthResDTO.SignUpResultDTO result = memberService.signUp(request);

        return ApiResponse.onSuccess(AuthSuccessCode.CREATED, result);
    }
}
