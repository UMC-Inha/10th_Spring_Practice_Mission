package umc.domain.member.dto;

import lombok.Builder;
import umc.domain.member.enums.Gender;
import umc.domain.member.enums.Org_cd;
import umc.domain.member.enums.Status;

import java.time.LocalDate;

public class MemberResDTO {

    @Builder
    public record GetMember(
            Long member_id,
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

    @Builder
    public record GetMemberMission(
            Long member_id,
            Long mission_id,
            String succ_yn,
            LocalDate user_start_dt
    ){}
}
