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
                .org_cd(member.getOrg_cd())
                .build();
    }

}