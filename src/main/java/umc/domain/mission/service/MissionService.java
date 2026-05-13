package umc.domain.mission.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
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

    public MissionResDTO.Pagination<MissionResDTO.MissionDTO> getMissions(
            Long memberId,
            boolean isCompleted,
            Integer pageSize,
            Integer pageNumber,
            String sort) {

        Sort sortInfo;
        if (sort!=null){
            sortInfo=Sort.by(sort).descending();
        }
        else{
            sortInfo=Sort.by("id").descending();
        }

        PageRequest pageRequest =  PageRequest.of(pageNumber, pageSize, sortInfo);

        Member member = memberRepository.findById(memberId).orElseThrow(
                ()->new MemberException(MemberErrorCode.MEMBER_NOT_FOUND)
        );

        Page<MemberMission> missionList = memberMissionRepository.findAllByMemberAndIsCompleted(member, isCompleted, pageRequest);

        Page<MissionResDTO.MissionDTO> missionDTOPage = missionList.map(MemberMission::getMission).map(MissionConverter::toGetMissionDTO);


        return MissionConverter.toPagination(missionDTOPage.getContent(), missionDTOPage.getNumber(), missionDTOPage.getSize());
    }
}
