package umc.domain.member.converter;

import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.dto.MemberReqDTO;
import umc.domain.member.entity.Member;
import umc.domain.member.entity.MemberMission;
import umc.domain.member.enums.Social_Type;
import umc.domain.member.enums.Status;
import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.entity.Mission;

public class MemberConverter {

    // 멤버 조회 - 마이페이지
    public static MemberResDTO.GetMemberDTO toGetMember(Member member) {
        return MemberResDTO.GetMemberDTO.builder()
                .member_id(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .gender(member.getGender())
                .birth(member.getBirth())
                .phone(member.getPhone())
                .point(member.getPoint())
                .status(member.getStatus())
                .build();
    }

    // 회원가입 생성 — 암호화된 비밀번호를 인자로 받음
    public static Member toPutMember(
            MemberReqDTO.SignUpDTO dto, String encodedPassword
    ) {
        return Member.builder()
                .email(dto.email())
                .password(encodedPassword) // ★ 암호화된 비밀번호 사용
                .name(dto.name())
                .gender(dto.gender())
                .birth(dto.birth())
                .post(dto.post())
                .add1(dto.add1())
                .add2(dto.add2())
                .phone(dto.phone())
                .point(0)
                .status(Status.ACTIVE)
                .social_provider(Social_Type.NONE)
                .build();
    }

    // 회원가입 조회
    public static MemberResDTO.GetSignUpDTO toGetSignUp(Member member) {

        return new MemberResDTO.GetSignUpDTO(
                member.getId(),
                member.getCreatedAt()
        );
    }

    // 로그인 인증
    public static MemberResDTO.LoginResponse toLoginResponse(String accessToken) {
        return new MemberResDTO.LoginResponse(accessToken);
    }

    // 멤버 미션 생성
    public static MemberMission toPutMemberMission(
            Mission mission, Member member
    ) {
        return MemberMission.builder()
                .mission(mission)
                .member(member)
                .succ_yn("N")  // 기본값 미완료
                .user_start_dt(java.time.LocalDate.now())
                .build();
    }

    // 멤버 미션 조회
    public static MemberResDTO.GetMemberMissionDTO toGetMemberMission(
            MemberMission memberMission
    ) {
        return MemberResDTO.GetMemberMissionDTO.builder()
                .member_id(memberMission.getMember().getId())
                .mission_id(memberMission.getMission().getId())
                .succ_yn(memberMission.getSucc_yn())
                .user_start_dt(memberMission.getUser_start_dt())
                .build();
    }

    // 홈 조회 추가


}