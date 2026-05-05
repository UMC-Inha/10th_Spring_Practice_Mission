package umc.member.converter;

import umc.member.dto.MemberResDTO;
import umc.member.entity.Member;

public class MemberConverter {
    public static MemberResDTO.GetInfo toGetInfo(
            Member member
    ){
        return MemberResDTO.GetInfo.builder()
                .email(member.getEmail())
                .name(member.getName())
                .point(member.getPoint())
                .profileUrl(member.getProfileUrl())
                .phoneNumber((member.getPhoneNumber()))
                .build();
    }
}
