package umc.domain.member.dto;

import jakarta.validation.constraints.*;
import umc.domain.member.enums.Gender;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    // id를 body로 받아서 조회 할 떄 사용
//    public record MyPageReqDTO(
//            @NotNull
//            Long id
//    ){}

    public record SignUpReq(
            @NotBlank
            @Email
            String email,
            @NotBlank
            String password,
            @NotBlank
            String name,
            @NotNull
            Gender gender,
            @NotNull
            @Past(message = "생년월일은 과거 날짜여야 합니다.")
            LocalDate birth,
            @NotBlank
            String address,
            @NotEmpty
            List<Long> agreedTermsIds,
            List<Long> userFood,
            String phoneNumber
    ){}

    public record LoginReq(
            @NotBlank
            @Email
            String email,
            @NotBlank
            String password
    ){}
}
