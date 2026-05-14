package umc.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.mission.converter.MissionConverter;
import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.entity.mapping.MemberMission;
import umc.domain.mission.enums.MemberMissionStatus;
import umc.domain.mission.repository.MemberMissionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;

    @Transactional(readOnly = true)
    public MissionResDTO.GetMissions getMissions(
            Long memberId,
            List<MemberMissionStatus> statuses,
            Integer page
    ){
        Pageable pageable = PageRequest.of(page, 10);

        Page<MemberMission> memberMissions =
                memberMissionRepository.findMyMissions(memberId, statuses, pageable);

        return MissionConverter.toGetMissions(memberMissions);
    }
}
