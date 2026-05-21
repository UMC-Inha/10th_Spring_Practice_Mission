package umc.domain.auth.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import umc.domain.member.enums.Gender;

public class AuthReqDTO {

    public record SignUpDTO(
            @NotBlank
            @Email
            String email,
            @NotBlank
            String name,
            @NotNull
            Gender gender,
            @NotBlank
            String password,
            @NotBlank
            @Pattern(regexp = "^\\d{4}-\\d{2}-\\d{2}$")
            String birth,
            @NotBlank
            String address
    ){}
}
