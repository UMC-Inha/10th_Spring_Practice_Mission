package umc.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import umc.apiPayload.dto.BaseResDTO;
import umc.mission.converter.MissionConverter;
import umc.mission.dto.MissionResDTO;
import umc.mission.entity.Mapping.MemberMission;
import umc.mission.enums.MissionStatus;
import umc.mission.repository.MemberMissionRepository;

@Service
@RequiredArgsConstructor
public class MemberMissionService {
    private final MemberMissionRepository memberMissionRepository;

    public BaseResDTO.Pagination<MissionResDTO.MissionInfoDTO> getInProgressMissions(
            Long memberId,
            Integer pageSize,
            Integer pageNumber,
            String sort
    ){
        Sort sortInfo;
        if(sort != null){
            sortInfo = Sort.by(sort);
        }else{
            sortInfo = Sort.by("mission.deadline").ascending();
        }

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

        Page<MemberMission> memberMissionPage = memberMissionRepository.findAllByMemberIdAndIsComplete(memberId, MissionStatus.IN_PROGRESS, pageRequest);

        return MissionConverter.toPagination(
                memberMissionPage.map(MissionConverter::MemberMissionToMissionInfoDTO).toList(),
                memberMissionPage.getNumber(),
                memberMissionPage.getSize()
        );
    }
}
