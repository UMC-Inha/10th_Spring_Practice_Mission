package umc.domain.member.converter;

import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.entity.Member;

public class MemberConverter {

    public static MemberResDTO.GetInfo toGetInfo(
            Member member
    ) {
        return MemberResDTO.GetInfo.builder()
                .log_id(member.getLog_id())
                .email(member.getEmail())
                .password(member.getPassword())
                .name(member.getName())
                .gender(member.getGender())
                .birth(member.getBirth())
                .post(member.getPost())
                .add1(member.getAdd1())
                .add2(member.getAdd2())
                .phone(member.getPhone())
                .point(member.getPoint())
                .status(member.getStatus())
                .crt_dt(member.getCrt_dt())
                .org_cd(member.getOrg_cd())
                .chg_dt(member.getChg_dt())
                .chg_tm(member.getChg_tm())
                .build();
    }

}