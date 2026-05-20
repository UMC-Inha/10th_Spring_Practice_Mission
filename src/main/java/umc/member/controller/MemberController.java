package umc.member.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.BaseSuccessCode;
import umc.member.dto.MemberReqDTO;
import umc.member.dto.MemberResDTO;
import umc.member.exception.code.MemberSuccessCode;
import umc.member.service.MemberService;
import umc.mission.dto.MissionResDTO;
import umc.mission.service.MissionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;
    private final MissionService missionService;

    @PostMapping("/v1/users/me")
    @Operation(summary = "마이페이지 조회")
    public ApiResponse<MemberResDTO.GetInfo> getInfo(
            @RequestBody MemberReqDTO.GetInfo dto
    ){
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, memberService.getInfo(dto));
    }

    @GetMapping("/v1/home")
    @Operation(summary = "지역 미션 조회")
    public ApiResponse<MissionResDTO.MissionListDTO> getHomeInfo(
            @RequestParam(name = "memberId") Long memberId,
            @RequestParam(name = "regionName") String regionName,
            @RequestParam(name = "page", defaultValue = "0") Integer page
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.OK, missionService.getHomeInfo(memberId, regionName, page));
    }

    @PostMapping("/v1/auth/signup")
    @Operation(summary = "회원가입")
    public ApiResponse<MemberResDTO.AuthResDTO.SignUpResultDTO> signUp(
            @RequestBody MemberReqDTO.SingUpDTO request
    ) {
        MemberResDTO.AuthResDTO.SignUpResultDTO result = memberService.signUp(request);

        return ApiResponse.onSuccess(MemberSuccessCode.JOIN_OK, result);
    }
}
