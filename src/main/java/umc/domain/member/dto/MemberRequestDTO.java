package umc.domain.member.dto;

import umc.domain.member.enums.Gender;

import java.time.LocalDateTime;
import java.util.List;

public class MemberRequestDTO {

    // 회원가입
    public record SignUpDTO(
            String name,
            Gender gender,
            String birth,
            String address,
            List<Long> foodIds   // 회원가입 시 선호 음식 선택
            List<TermDTO> terms;   // 회원가입 시 약관 동의
    ) {}

    // 약관
    public record TermDTO(
            Long termId,
            Boolean isAgreed
    ) {}

}
