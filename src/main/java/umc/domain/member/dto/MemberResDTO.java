package umc.domain.member.dto;

import lombok.Builder;

public class MemberResDTO {

    @Builder
    public record GetInfo(
            String log_id,
            String email,
            String password,
            String name,
            String gender,
            String birth,
            String post,
            String add1,
            String add2,
            String phone,
            Integer point,
            String status,
            String crt_dt,
            String org_cd,
            String chg_dt,
            String chg_tm
    ){}
}
