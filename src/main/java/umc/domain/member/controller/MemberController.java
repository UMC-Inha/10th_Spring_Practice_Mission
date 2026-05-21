package umc.domain.member.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.member.dto.MemberRequestDTO;
import umc.domain.member.dto.MemberResponseDTO;
import umc.domain.member.exception.code.MemberSuccessCode;
import umc.domain.member.service.MemberService;
import umc.global.apiPayload.ApiResponse;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    // 회원가입
    @PostMapping("/signup")
    public ApiResponse<MemberResponseDTO.SignUpDTO> signUp(
            @RequestBody @Valid MemberRequestDTO.SignUpDTO requestDto
    ) {
        return ApiResponse.onSuccess(MemberSuccessCode.CREATED, memberService.signUp(requestDto));
    }

    // 홈 화면 조회
    @GetMapping("/me/home")
    public ApiResponse<MemberResponseDTO.HomeDTO> getHome(
            @RequestParam String regionName,
            @RequestParam(defaultValue = "10") int pageSize,
            @RequestParam(defaultValue = "0") int page
    ) {
        Long memberId = 1L;
        MemberResponseDTO.HomeDTO responseDto = memberService.getHome(memberId, regionName, page, pageSize);
        return ApiResponse.onSuccess(MemberSuccessCode.HOME_VIEW, responseDto);
    }

    // 마이페이지
    @GetMapping("/me")
    public ApiResponse<MemberResponseDTO.MyPageDTO> getMyPage() {
        MemberResponseDTO.MyPageDTO responseDto = memberService.getMyPage(1L);
        return ApiResponse.onSuccess(MemberSuccessCode.MY_PAGE_VIEW, responseDto);
    }













}
