package umc.domain.member.dto;

import umc.domain.member.enums.Gender;

public class MemberReqDTO {

    public record SignUpDTO(
            String name,
            Gender gender,
            String birth,
            String address
    ){}
}
