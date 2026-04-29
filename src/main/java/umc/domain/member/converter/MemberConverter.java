package umc.domain.member.converter;

public class MemberConverter {
}

public static MemberResDTO.GetInfo toGetInfo(
        Member member
){
    return MemberResDTO.GetInfo.builder()
            .email(member.getEmail())
            .name(member,getName())
            .point(member.getPoint())
            .phoneNumber(member.getPhoneNumber())
            .profileUrl(member.getProfileUrl())
            .build();
}
