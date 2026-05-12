package umc.domain.mission.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import umc.domain.member.entity.Member;
import umc.domain.member.exception.MemberException;
import umc.domain.member.exception.code.MemberErrorCode;
import umc.domain.member.repository.MemberRepository;
import umc.domain.mission.converter.MissionConverter;
import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.entity.Mission;
import umc.domain.mission.entity.mapping.MemberMission;
import umc.domain.mission.repository.MemberMissionRepository;
import umc.domain.mission.repository.MissionRepository;

import java.util.List;

@Service
@AllArgsConstructor
public class MissionService {

    private final MemberRepository memberRepository;
    private final MemberMissionRepository memberMissionRepository;

    public MissionResDTO.MissionList getMissions(Long memberId, boolean isCompleted) {

        Member member = memberRepository.findById(memberId).orElseThrow(
                ()->new MemberException(MemberErrorCode.MEMBER_NOT_FOUND)
        );

        List<MemberMission> missionList = memberMissionRepository.findAllByMemberAndIsCompleted(member, isCompleted);

        List<Mission> missions = missionList.stream()
                .map(MemberMission::getMission)
                .toList();

        return MissionConverter.toGetMissions(missions);
    }
}
