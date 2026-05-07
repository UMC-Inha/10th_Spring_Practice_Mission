package umc.domain.member.converter;

import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.entity.Member;

public class MemberConverter {

    public static MemberResDTO.MyPageViewDTO toMyPageViewDTO(Member member){
        return MemberResDTO.MyPageViewDTO.builder()
                .id(member.getId())
                .profileUrl(member.getProfileUrl())
                .nickname(member.getNickname())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber())
                .points(member.getPoints())
                .build();
    }
}
