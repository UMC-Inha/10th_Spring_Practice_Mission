package umc.domain.member.converter;

import umc.domain.member.dto.MemberResponseDTO;
import umc.domain.member.entity.Member;


public class MemberConverter {
    public static MemberResponseDTO.GetInfo toGetInfo(
            Member member
    ) {
        return MemberResponseDTO.GetInfo.builder()
                .email(member.getEmail())
                .name(member.getName())
                .profileUrl(member.getProfileUrl())
                .phoneNumber(member.getPhoneNumber())
                .point(member.getPoint())
                .build();
    }
}
