package umc.domain.member.controller;

import io.swagger.v3.oas.annotations.Operation;
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
@RequestMapping("/auth")
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/users")
    @Operation(summary = "회원가입")
    public ApiResponse<MemberResDTO.JoinResultDTO> join(
            @RequestBody MemberReqDTO.JoinDTO request
    ) {
        MemberResDTO.JoinResultDTO result = memberService.join(request);
        BaseSuccessCode code = MemberSuccessCode.OK;
        return ApiResponse.onSuccess(code, result);
    }
}
