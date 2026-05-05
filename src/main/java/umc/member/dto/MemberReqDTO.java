package umc.member.dto;

import umc.member.enums.SocialType;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record GetInfo(
            Long id
    ){}

    public record SingUpDTO(
            String email,
            String password,
            String name,
            String gender,
            LocalDate birth,
            String address,
            String socailUid,
            String socailType,
            List<Long> agreedTerms,
            List<Long> userFoods
    ) {}
}
