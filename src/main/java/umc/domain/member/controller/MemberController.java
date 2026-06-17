package umc.domain.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import umc.domain.member.dto.MemberReqDTO;
import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.exception.code.MemberSuccessCode;
import umc.domain.member.service.MemberService;
import umc.domain.mission.dto.MissionResDTO;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.BaseSuccessCode;
import umc.global.security.entity.AuthMember;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/members")
public class MemberController {

    private final MemberService memberService;

    // 멤버 조회 - 마이페이지
    @PostMapping("/me")
    public ApiResponse<MemberResDTO.GetMemberDTO> getMember(
            @RequestBody MemberReqDTO.GetMemberDTO dto
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.MEMBER_OK,
                memberService.getMember(dto.id()));
    }

    // 마이페이지
    @GetMapping("/me/v2")
    public ApiResponse<MemberResDTO.GetMemberDTO> getMember(
            @AuthenticationPrincipal AuthMember member
    ) {
        BaseSuccessCode code = MemberSuccessCode.MEMBER_OK;
        return ApiResponse.onSuccess(code, memberService.getMember(member));
    }

    // 회원가입
    @PostMapping("/signup")
    public ApiResponse<MemberResDTO.GetSignUpDTO> signUp(
            @RequestBody @Valid MemberReqDTO.SignUpDTO requestDto
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.MEMBER_CREATED, memberService.signUp(requestDto));
    }

    // 로그인
    @PostMapping("/login")
    public ApiResponse<MemberResDTO.LoginResponse> login(
            @RequestBody MemberReqDTO.LoginRequest request
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.LOGIN_SUCCESS, memberService.login(request));
    }

    // 내 미션 생성
    @PostMapping("/me/missions")
    public ApiResponse<Void> createMyMission(
            @RequestParam Long memberId,
            @RequestParam Long missionId
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.MISSION_CREATED,
                memberService.createMyMission(memberId, missionId));  // ← 메서드명 수정
    }

    // 내 미션 조회
    @GetMapping("/me/missions")
    public ApiResponse<List<MemberResDTO.GetMemberMissionDTO>> getMyMissions(
            @RequestParam Long memberId,
            @RequestParam Integer pageSize,       // ← missionId 제거
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.MEMBER_MISSION_OK,
                memberService.getMyMissions(memberId, pageSize, pageNumber, sort));  // ← 메서드명 수정
    }
}