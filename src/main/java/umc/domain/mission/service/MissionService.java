package umc.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.domain.member.entity.mapping.MemberMission;
import umc.domain.member.repository.MemberMissionRepository;
import umc.domain.mission.converter.MissionConverter;
import umc.domain.mission.dto.MissionResponseDTO;
import umc.domain.mission.entity.Mission;
import umc.domain.mission.enums.MissionStatus;
import umc.domain.mission.repository.MissionRepository;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;

    public MissionResponseDTO.MissionList getMissions(Long memberId, MissionStatus status, Integer page) {
        Page<MemberMission> memberMissions = memberMissionRepository
                .findByMemberIdAndStatus(memberId, status, PageRequest.of(page, 10));
        return MissionConverter.toMissionList(memberMissions.getContent());
    }

    public MissionResponseDTO.HomeMissionList getHomeMissions(String regionName, Integer page) {
        Long memberId = 1L;
        Page<Mission> missions = missionRepository
                .findMissionsByRegion(memberId, regionName, PageRequest.of(page, 10));
        Long completedCount = memberMissionRepository
                .countCompletedMissionsByRegion(memberId, regionName);
        return MissionConverter.toHomeMissionList(regionName, 999999, completedCount, missions.getContent());
    }
}
