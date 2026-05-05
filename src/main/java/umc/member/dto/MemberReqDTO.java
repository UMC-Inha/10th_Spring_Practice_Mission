package umc.member.dto;

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
            List<Long> agreedTerms,
            List<Long> userFoods
    ) {}
}
