package umc.domain.member.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import umc.domain.member.enums.Gender;
import umc.domain.member.enums.Social_Type;
import umc.domain.member.enums.Status;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    // Member 조회
    public record GetMemberDTO(
            Long id
    ){}

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
            String post,
            @NotBlank
            String add1,    // 시, 구
            @NotBlank
            String add2,    // 상세주소 동, 호
            @NotBlank
            String phone,

            List<Long> foodId,
            @NotNull
            @NotEmpty
            @Valid
            List<TermDTO> terms
    ){}

    // 약관
    public record TermDTO(
            @NotNull
            Long termId,
            @NotNull
            Boolean isAgreed
    ) {}



}
