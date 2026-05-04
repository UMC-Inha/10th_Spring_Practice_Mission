package umc.domain.member.dto;

import umc.domain.member.enums.Gender;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record MyPageReqDTO(
            Long id
    ){}

    public record SignUpReq(
            List<Integer> agreedTermsIds,
            String name,
            Gender gender,
            LocalDate birth,
            String address,
            List<Integer> userFood
    ){}
}
