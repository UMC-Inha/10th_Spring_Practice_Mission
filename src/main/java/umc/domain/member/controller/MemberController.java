package umc.domain.member.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
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

	// 마이페이지
	@PostMapping("/v1/users/me")
	public ApiResponse<MemberResDTO.GetInfo> getInfo(
		@RequestBody MemberReqDTO.GetInfo dto
	) {
		BaseSuccessCode code = MemberSuccessCode.OK;
		return ApiResponse.onSuccess(code, memberService.getInfo(dto));
	}

	// 회원가입
	@PostMapping("/signup")
	public ApiResponse<String> join(@RequestBody MemberReqDTO.JoinDTO request) {
		BaseSuccessCode code = MemberSuccessCode.MEMBER_JOINED;
		return ApiResponse.onSuccess(code, memberService.join(request));
	}
}
