package umc.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Past;
import jakarta.validation.constraints.Pattern;
import umc.domain.member.enums.Gender;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record MyPageReqDTO(
            @NotNull
            Long id
    ){}

    public record SignUpReq(
            List<Integer> agreedTermsIds,
            @NotBlank
            String name,
            @NotNull
            Gender gender,
            @NotNull
            @Past(message = "생년월일은 과거 날짜여야 합니다.")
            LocalDate birth,
            @NotBlank
            String address,
            List<Integer> userFood
    ){}
}
