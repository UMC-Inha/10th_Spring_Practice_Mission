package umc.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import umc.domain.member.enums.Gender;

public class MemberReqDTO {

    public record SignUpDTO(
            @NotBlank
            String name,
            @NotNull
            Gender gender,
            @NotBlank
            @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
            String birth,
            @NotBlank
            String address
    ){}
}
