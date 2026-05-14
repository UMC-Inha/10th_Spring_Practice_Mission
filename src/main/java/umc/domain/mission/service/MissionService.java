package umc.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.mission.converter.MissionConverter;
import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.entity.mapping.MemberMission;
import umc.domain.mission.enums.MissionStatus;
import umc.domain.mission.repository.MemberMissionRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;

    public MissionResDTO.MissionListDto getMissions(
            Long memberId,
            List<MissionStatus> statuses,
            LocalDate cursorDueDate,
            Long cursorId,
            int pageSize
    ) {
        List<MemberMission> memberMissions;

        if (cursorDueDate == null || cursorId == null){
            cursorDueDate = LocalDate.of(1900,1,1);
            cursorId = -1L;
        }

        memberMissions = memberMissionRepository.findMemberMissionsUsingCursor(
                memberId,
                statuses,
                cursorDueDate,
                cursorId,
                pageSize+1
        );

        boolean hasNextPage = memberMissions.size() > pageSize;
        List<MemberMission> results = new ArrayList<>(
                memberMissions.subList(0, hasNextPage ? pageSize : memberMissions.size())
        );

        return MissionConverter.toMissionListDto(results, hasNextPage);
    }
}
