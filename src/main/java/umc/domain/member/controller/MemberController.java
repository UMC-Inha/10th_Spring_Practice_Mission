package umc.domain.member.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.member.dto.MemberReqDTO;
import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.exception.code.MemberSuccessCode;
import umc.domain.member.service.MemberService;
import umc.global.apiPayload.ApiResponse;

import java.time.LocalDate;

@RestController
@RequestMapping("/api/members")
@RequiredArgsConstructor
public class MemberController {

    private final MemberService memberService;

    @PostMapping("/signup")
    public ApiResponse<MemberResDTO.SignUpDTO> signUp(
            @RequestBody MemberReqDTO.SignUpDTO reqDto
    ){
        return ApiResponse.onSuccess(MemberSuccessCode.CREATED, null);
    }

    @GetMapping("/home")
    public ApiResponse<MemberResDTO.HomeViewDTO> getHome(
            @RequestParam String regionName,
            @RequestParam(required = false) LocalDate cursorDueDate,
            @RequestParam(required = false) Long cursorId,
            @RequestParam(defaultValue = "10") int pageSize
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
    public ApiResponse<MemberResDTO.MyPageViewDTO> getMyPage(){
        MemberResDTO.MyPageViewDTO resDto = memberService.getMyPage(1L);
        return ApiResponse.onSuccess(MemberSuccessCode.MY_PAGE_VIEW, resDto);
    }
}
