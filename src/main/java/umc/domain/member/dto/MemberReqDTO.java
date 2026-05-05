package umc.domain.member.dto;

import java.time.LocalDate;

public class MemberReqDTO {

    public record JoinDTO(
            String name,
            String gender,
            String address,
            LocalDate birth,
            String mail,
            String phoneNumber
    ){}
}
