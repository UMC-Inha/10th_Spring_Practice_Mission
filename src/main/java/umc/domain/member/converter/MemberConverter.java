package umc.domain.member.converter;

import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.dto.MemberReqDTO;
import umc.domain.member.entity.Member;
import umc.domain.member.entity.MemberMission;
import umc.domain.mission.entity.Mission;

public class MemberConverter {
    // 멤버 생성
    public static Member toMember(
            MemberReqDTO.CreateMember dto
    ) {
        return Member.builder()
                .log_id(dto.log_id())
                .email(dto.email())
                .password(dto.password())
                .name(dto.name())
                .gender(dto.gender())
                .birth(dto.birth())
                .post(dto.post())
                .add1(dto.add1())
                .add2(dto.add2())
                .phone(dto.phone())
                .point(dto.point())
                .status(dto.status())
                .org_cd(dto.org_cd())
                .build();
    }

    // 멤버 조회
    public static MemberResDTO.GetMember toGetMember(
            Member member
    ) {
        return MemberResDTO.GetMember.builder()
                .member_id(member.getId())
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

    // 멤버 미션 생성
    public static MemberMission toMemberMission(
            Mission mission,
            Member member,
            MemberReqDTO.CreateMemberMission dto
    ) {
        return MemberMission.builder()
                .mission(mission)
                .member(member)
                .succ_yn(dto.succ_yn())
                .user_start_dt(dto.user_start_dt())
                .build();
    }

    // 멤버 미션 조회
    public static MemberResDTO.GetMemberMission toGetMemberMission(
            MemberMission memberMission
    ){
        return MemberResDTO.GetMemberMission.builder()
                .member_id(memberMission.getMember().getId())
                .mission_id(memberMission.getMission().getId())
                .succ_yn(memberMission.getSucc_yn())
                .user_start_dt(memberMission.getUser_start_dt())
                .build();

    }

}