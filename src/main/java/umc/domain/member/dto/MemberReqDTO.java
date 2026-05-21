package umc.domain.member.dto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import umc.domain.category.enums.CategoryName;
import umc.domain.member.enums.Gender;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record GetInfo(
            Long id
    ){}

    public record CreateMember(
            @NotNull @Valid Agree agree,
            @NotBlank String name,
            @NotNull Gender gender,
            @NotNull LocalDate birth,
            @NotBlank String address,
            @NotEmpty List<@NotNull CategoryName> categoryNameList,
            @Email @NotBlank String email,
            @NotBlank String password
    ) {
        public record Agree(
                @NotNull Boolean age,
                @NotNull Boolean service,
                @NotNull Boolean privacy,
                @NotNull Boolean location,
                @NotNull Boolean marketing
        ) {}
    }
}
