package umc.domain.member.dto;

import umc.domain.member.enums.Gender;

import java.util.Date;
import java.util.List;

public class MemberReqDTO {

    public record MyPageReqDTO(
            Long id
    ){}

    public record SignUpReq(
            List<Integer> agreedTermsIds,
            String name,
            Gender gender,
            Date birth,
            String address,
            List<Integer> user_food
    ){}
}
