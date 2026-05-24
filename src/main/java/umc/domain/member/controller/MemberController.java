package umc.domain.member.controller;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.exception.code.MemberSuccessCode;
import umc.domain.member.service.MemberService;
import umc.global.apiPayload.ApiResponse;
import umc.global.security.entity.AuthMember;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
@Validated
public class MemberController {

    private final MemberService memberService;

    @GetMapping("/home")
    public ApiResponse<MemberResDTO.HomeViewDTO> getHome(
            @RequestParam @NotBlank String regionName,
            @RequestParam(required = false) LocalDate cursorDueDate,
            @RequestParam(required = false) Long cursorId,
            @RequestParam(defaultValue = "10") @Min(1) int pageSize
    ) {
        MemberResDTO.HomeViewDTO resDto = memberService.getHome(
                1L,
                regionName,
                cursorDueDate,
                cursorId,
                pageSize
        );
        return ApiResponse.onSuccess(MemberSuccessCode.HOME_VIEW, resDto);
    }

    @GetMapping("/me")
    public ApiResponse<MemberResDTO.MyPageViewDTO> getMyPage(
            @AuthenticationPrincipal AuthMember authMember
            ){
        MemberResDTO.MyPageViewDTO resDto = memberService.getMyPage(authMember.getMember().getId());
        return ApiResponse.onSuccess(MemberSuccessCode.MY_PAGE_VIEW, resDto);
    }
}
