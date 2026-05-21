package umc.domain.member.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import umc.domain.member.enums.Gender;

import java.time.LocalDateTime;
import java.util.List;

public class MemberRequestDTO {

    // 회원가입
    public record SignUpDTO(
            @NotBlank @Email
            String email,
            @NotBlank
            String password,
            @NotBlank
            String name,
            @NotNull
            Gender gender,
            @NotBlank
            String birth,
            @NotBlank
            String address,
            List<Long> foodIds, // 회원가입 시 선호 음식 선택
            @NotNull
            @NotEmpty
            @Valid
            List<TermDTO> terms // 회원가입 시 약관 동의
    ) {}

    // 약관
    public record TermDTO(
            @NotNull
            Long termId,
            @NotNull
            Boolean isAgreed
    ) {}

}
