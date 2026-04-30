package umc.domain.member.dto;

import umc.domain.member.enums.Gender;

import java.time.LocalDate;

public class MemberReqDTO {

    public record GetInfo(
            Long id
    ){}

    public record CreateUser(
            String name,
            Gender gender,
            LocalDate birth,
            String address
    ) {}
}
