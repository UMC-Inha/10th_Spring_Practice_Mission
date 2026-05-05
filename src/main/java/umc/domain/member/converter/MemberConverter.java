package umc.domain.member.converter;

import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.entity.Member;

public class MemberConverter {

    public static MemberResDTO.MyProfileDTO toMyProfileDTO(Member member){
        return new MemberResDTO.MyProfileDTO(
                member.getNickname(),
                member.getPhotoUrl(),
                member.getMail(),
                member.getPhoneNumber(),
                member.getPhoneNumber() != null && !member.getPhoneNumber().isEmpty(),
                member.getUserPoint()
        );
    }
}
