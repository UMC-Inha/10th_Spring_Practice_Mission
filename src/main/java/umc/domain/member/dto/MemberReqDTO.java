package umc.domain.member.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import umc.domain.member.enums.Gender;

import java.time.LocalDate;

public class MemberReqDTO {

    public record GetInfo(
            Long id
    ){}

    public record CreateMember(
            @NotBlank String name,
            @NotNull Gender gender,
            @NotNull LocalDate birth,
            @NotBlank String address
    ) {}
}
