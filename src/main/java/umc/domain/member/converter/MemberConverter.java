package umc.domain.member.converter;

import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.entity.Member;

public class MemberConverter {
    public static MemberResDTO.MyPageResDTO toGetInfo(Member member) {
        return MemberResDTO.MyPageResDTO.builder()
                .memberId(member.getId())
                .name(member.getName())
                .email(member.getMail())
                .phoneNumber(member.getPhoneNumber() != null ? member.getPhoneNumber(): "미인증")
                .point(member.getPoint())
                .build();
    }

    public static MemberResDTO.PointResDTO toGetPoint(Member member) {
        return MemberResDTO.PointResDTO.builder()
                .point(member.getPoint())
                .build();
    }
}
