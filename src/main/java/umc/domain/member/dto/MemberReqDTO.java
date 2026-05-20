package umc.domain.member.dto;

import umc.domain.member.enums.Gender;
import umc.domain.member.enums.Org_cd;
import umc.domain.member.enums.Status;

import java.time.LocalDate;

public class MemberReqDTO {
    public record CreateMember(
            String log_id,
            String email,
            String password,
            String name,
            Gender gender,
            String birth,
            String post,
            String add1,
            String add2,
            String phone,
            Integer point,
            Status status,
            Org_cd org_cd
    ){}

    public record CreateMemberMission(
            String succ_yn,
            LocalDate user_start_dt
    ){}
}
