package umc.domain.member.converter;

import jakarta.validation.Valid;
import umc.domain.member.dto.MemberReqDTO;
import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.entity.Member;

public class MemberConverter {
    public static MemberResDTO.MyPageResDTO toGetInfo(Member member) {
        return MemberResDTO.MyPageResDTO.builder()
                .memberId(member.getId())
                .name(member.getName())
                .email(member.getEmail())
                .phoneNumber(member.getPhoneNumber() != null ? member.getPhoneNumber(): "미인증")
                .point(member.getPoint())
                .build();
    }

    public static MemberResDTO.PointResDTO toGetPoint(Member member) {
        return MemberResDTO.PointResDTO.builder()
                .point(member.getPoint())
                .build();
    }

    public static Member toMemberEntity(MemberReqDTO.@Valid SignUpReq dto) {
        return Member.builder()
                .name(dto.name())
                .gender(dto.gender())
                .birth(dto.birth())
                .address(dto.address())
                .phoneNumber(dto.phoneNumber())
                .email(dto.email())
                .password(dto.password())
                .point(0)
                .build();
    }

    public static MemberResDTO.SignUpRes toSignUpRes(Member savedMember){
        return MemberResDTO.SignUpRes.builder()
                .memberId(savedMember.getId())
                .createdAt(savedMember.getCreatedAt())
                .build();
    }
}
