package umc.domain.auth.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.auth.dto.AuthReqDTO;
import umc.domain.auth.dto.AuthResDTO;
import umc.domain.auth.service.AuthService;
import umc.domain.member.dto.MemberReqDTO;
import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.exception.code.MemberSuccessCode;
import umc.global.apiPayload.ApiResponse;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final AuthService authService;

    @PostMapping("/signup")
    public ApiResponse<AuthResDTO.SignUpDTO> signUp(
            @RequestBody @Valid AuthReqDTO.SignUpDTO reqDto
    ){
        AuthResDTO.SignUpDTO resDto = authService.signUp(reqDto);
        return ApiResponse.onSuccess(MemberSuccessCode.CREATED, resDto);
    }
}
