package umc.domain.member.controller;

import jakarta.validation.Valid;
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
@RequestMapping("/public")
public class PublicMemberController {

    private final MemberService memberService;

    @PostMapping("/users")
    public ApiResponse<MemberResDTO.CreateMember> createMember(
            @RequestBody @Valid MemberReqDTO.CreateMember request
    ) {
        BaseSuccessCode code = MemberSuccessCode.MEMBER_CREATED;
        return ApiResponse.onSuccess(code, memberService.createMember(request));
    }
}
