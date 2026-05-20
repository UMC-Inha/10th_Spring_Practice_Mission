package umc.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.member.dto.MemberReqDTO;
import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.exception.code.MemberSuccessCode;
import umc.domain.member.service.MemberService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.BaseSuccessCode;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MemberController {

    private final MemberService memberService;

    // 멤버 생성
    @PostMapping("/v1/member/me")
    public ApiResponse<Void> createMember(
            @RequestBody MemberReqDTO.CreateMember dto  // ✅ @PathVariable → @RequestBody
    ) {
        BaseSuccessCode code = MemberSuccessCode.CREATED;
        return ApiResponse.onSuccess(code, memberService.createMember(dto));
    }

    // 멤버 조회
    @GetMapping("/v1/member/me")  // ✅ @PostMapping → @GetMapping
    public ApiResponse<List<MemberResDTO.GetMember>> getMembers(
            @RequestParam Integer pageSize,       // ✅ @PathVariable → @RequestParam
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort
    ){
        BaseSuccessCode code = MemberSuccessCode.OK;  // ✅ MissionSuccessCode → MemberSuccessCode
        return ApiResponse.onSuccess(code, memberService.getMembers(pageSize, pageNumber, sort));
    }

    // 멤버 미션 생성
    @PostMapping("/v1/member/missions")
    public ApiResponse<Void> createMemberMissions(
            @RequestParam Long memberId,    // ✅ 추가
            @RequestParam Long missionId,
            @RequestBody MemberReqDTO.CreateMemberMission dto  // ✅ @PathVariable → @RequestBody
    ) {
        BaseSuccessCode code = MemberSuccessCode.MISSIONCREATED;
        return ApiResponse.onSuccess(code, memberService.createMemberMission(memberId, missionId, dto));
    }

    // 멤버 미션 조회
    @GetMapping("/v1/member/missions")  // ✅ @PostMapping → @GetMapping
    public ApiResponse<List<MemberResDTO.GetMemberMission>> getMemberMissions(
            @RequestParam Long memberId,   // ✅ 추가
            @RequestParam Long missionId,
            @RequestParam Integer pageSize,       // ✅ @PathVariable → @RequestParam
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort
    ){
        BaseSuccessCode code = MemberSuccessCode.MISSIONOK;  // ✅ MissionSuccessCode → MemberSuccessCode
        return ApiResponse.onSuccess(code, memberService.getMemberMissions(memberId, missionId, pageSize, pageNumber, sort));
    }
}