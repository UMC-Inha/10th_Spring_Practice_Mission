package umc.domain.member.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import umc.domain.member.converter.MemberConverter;
import umc.domain.member.dto.MemberReqDTO;
import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.entity.Member;
import umc.domain.member.entity.MemberMission;
import umc.domain.member.exception.MemberException;
import umc.domain.member.exception.code.MemberErrorCode;
import umc.domain.member.repository.MemberMissionRepository;
import umc.domain.member.repository.MemberRepository;
import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.entity.Mission;
import umc.domain.mission.repository.MissionRepository;

import javax.swing.plaf.synth.Region;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    private final PasswordEncoder passwordEncoder;

    // Member 조회
    public MemberResDTO.GetMemberDTO getMember(Long id) {
        Member member = memberRepository.findById(id)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return MemberConverter.toGetMember(member);
    }

    // 회원가입
    @Transactional
    public MemberResDTO.GetSignUpDTO signUp(MemberReqDTO.SignUpDTO dto) {
        validateEmailNotDuplicate(dto.email());
        Member member = createMember(dto);
        // 선호 음식 생성 savePreferFoods(member, dto.foodIds());
        // 약관 생성 saveTermAgreements(member, dto.terms());
        return MemberConverter.toGetSignUp(member);
    }

    private void validateEmailNotDuplicate(String email) {
        if (memberRepository.existsByEmail(email)) {
            throw new MemberException(MemberErrorCode.DUPLICATE_EMAIL);
        }
    }

    // 멤버 생성
    private Member createMember(MemberReqDTO.SignUpDTO dto) {
        // ★ 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(dto.password());
        // 멤버 생성
        Member member = MemberConverter.toPutMember(dto, encodedPassword);
        // 멤버 DB 저장
        return memberRepository.save(member);
    }

    // 선호음식 + 약관 추가

    // 내 미션 생성
    @Transactional
    public Void createMyMission(Long memberId, Long missionId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_MISSION_NOT_FOUND));
        MemberMission memberMission = MemberConverter.toPutMemberMission(mission, member);
        memberMissionRepository.save(memberMission);
        return null;
    }

    // 내 미션 조회
    public List<MemberResDTO.GetMemberMissionDTO> getMyMissions(
            Long memberId,
            Integer pageSize,
            Integer pageNumber,
            String sort
    ){
        Sort sortInfo;
        if(sort != null){
            if(sort.equalsIgnoreCase("asc")){
                sortInfo = Sort.by("id").ascending();
            } else if(sort.equalsIgnoreCase("desc")){
                sortInfo = Sort.by("id").descending();
            } else {
                sortInfo = Sort.by(sort);
            }
        } else {
            sortInfo = Sort.by("id").descending();
        }
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);
        Page<MemberMission> memberMissionList = memberMissionRepository.findAllByMember_Id(memberId, pageRequest);
        return memberMissionList.map(MemberConverter::toGetMemberMission).getContent();
    }

}
