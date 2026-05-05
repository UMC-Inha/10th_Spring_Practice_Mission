package umc.mission.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.mission.converter.MissionConverter;
import umc.mission.dto.MissionResDTO;
import umc.mission.entity.Mapping.MemberMission;
import umc.mission.enums.MissionStatus;
import umc.mission.exception.MissionException;
import umc.mission.exception.code.MissionErrorCode;
import umc.mission.repository.MemberMissionRepository;
import umc.mission.repository.MissionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {
    private final MissionRepository missionRepository;
    private final MemberMissionRepository memberMissionRepository;

    public MissionResDTO.MissionListDTO getHomeInfo(String regionName, Integer page) {
        return null;
    }

    public MissionResDTO.MissionListDTO getMyMissions(Boolean isComplete, Integer page) {
        Long memberId = 1L;

        MissionStatus status = isComplete ? MissionStatus.COMPLETE : MissionStatus.IN_PROGRESS;

        PageRequest pageRequest = PageRequest.of(page, 10);

        Page<MemberMission> missionPage = memberMissionRepository.findMyMissions(memberId, status, pageRequest);


        return MissionConverter.toMissionListDTO(missionPage);
    }

    @Transactional
    public MissionResDTO.MissionCompleteResultDTO completeMissions(Long userMissionId) {
        MemberMission memberMission = memberMissionRepository.findById(userMissionId)
                .orElseThrow(() -> new MissionException(MissionErrorCode.MISSION_NOT_FOUND));

        memberMission.completeMission();

        return MissionResDTO.MissionCompleteResultDTO.builder()
                .userMissionId(memberMission.getId())
                .build();
    }

}
