package umc.domain.member.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
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
import umc.domain.mission.entity.Mission;
import umc.domain.mission.repository.MissionRepository;


import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    private final PasswordEncoder passwordEncoder;

    // 멤버 생성
    @Transactional
    public Void createMember(
            MemberReqDTO.CreateMember dto
    ){
        // ★ 비밀번호 암호화
        String encodedPassword = passwordEncoder.encode(dto.password());

        // 멤버 생성
        Member member = MemberConverter.toMember(dto, encodedPassword);

        // 멤버 DB 저장
        memberRepository.save(member);

        return null;
    }

    // 멤버 호출
    public List<MemberResDTO.GetMember> getMembers(
            Integer pageSize,
            Integer pageNumber,
            String sort
    ){
        // 정렬 정보 생성
        Sort sortInfo;
        if(sort != null){
            if(sort.equalsIgnoreCase("asc")){
                sortInfo = Sort.by("id").ascending();
            } else if(sort.equalsIgnoreCase("desc")){
                sortInfo = Sort.by("id").descending();
            } else {
                sortInfo = Sort.by(sort); // 컬럼명으로 정렬
            }
        } else {
            sortInfo = Sort.by("id").descending();
        }

        // 페이지 정보들을 PageRequest로 만들기
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

        // 가게 내 아이디 조회
        Page<Member> memberList = memberRepository.findAll(pageRequest);


        // 미션들 응답 DTO로 포장하기
        return memberList.map(MemberConverter::toGetMember).getContent();
    }

    // 멤버 미션 생성
    @Transactional
    public Void createMemberMission(
            Long memberId,
            Long missionId,
            MemberReqDTO.CreateMemberMission dto
    ){
        // 멤버 찾기
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        // 미션 찾기
        Mission mission = missionRepository.findById(missionId)
                .orElseThrow(() ->new MemberException(MemberErrorCode.MEMBER_MISSION_NOT_FOUND));

        // 미션 생성
        MemberMission memberMission = MemberConverter.toMemberMission(mission, member, dto);

        // 미션 DB 저장
        memberMissionRepository.save(memberMission);

        return null;
    }

    // 멤버 미션 조회
    public List<MemberResDTO.GetMemberMission> getMemberMissions(
            Long memberId,
            Long missionId,
            Integer pageSize,
            Integer pageNumber,
            String sort
    ){
        // 정렬 정보 생성
        Sort sortInfo;
        if(sort != null){
            if(sort.equalsIgnoreCase("asc")){
                sortInfo = Sort.by("id").ascending();
            } else if(sort.equalsIgnoreCase("desc")){
                sortInfo = Sort.by("id").descending();
            } else {
                sortInfo = Sort.by(sort); // 컬럼명으로 정렬
            }
        } else {
            sortInfo = Sort.by("id").descending();
        }

        // 페이지 정보들을 PageRequest로 만들기
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

        // 멤버 미션 아이디 조회
        Page<MemberMission> memberMissionList = memberMissionRepository.findAllByMember_IdAndMission_Id(memberId, missionId, pageRequest);


        // 미션들 응답 DTO로 포장하기
        return memberMissionList.map(MemberConverter::toGetMemberMission).getContent();
    }

}