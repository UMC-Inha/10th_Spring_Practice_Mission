package umc.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.apiPayload.dto.BaseResDTO;
import umc.location.enums.LocationName;
import umc.location.exception.LocationException;
import umc.location.exception.code.LocationErrorCode;
import umc.location.repository.LocationRepository;
import umc.mission.converter.MissionConverter;
import umc.mission.dto.MissionReqDTO;
import umc.mission.dto.MissionResDTO;
import umc.mission.entity.Mapping.MemberMission;
import umc.mission.entity.Mission;
import umc.mission.enums.MissionStatus;
import umc.mission.exception.MissionException;
import umc.mission.exception.code.MissionErrorCode;
import umc.mission.repository.MemberMissionRepository;
import umc.mission.repository.MissionRepository;
import umc.store.entity.Store;
import umc.store.exception.StoreException;
import umc.store.exception.code.StoreErrorCode;
import umc.store.repository.StoreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {
    private final MissionRepository missionRepository;
    private final LocationRepository locationRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final StoreRepository storeRepository;

    public MissionResDTO.MissionListDTO getHomeInfo(Long memberId, String locationName, Integer page) {
        LocationName location = LocationName.fromLabel(locationName);

        if (!locationRepository.existsByName(location)) {
            throw new LocationException(LocationErrorCode.LOCATION_ERROR_CODE);
        }

        PageRequest pageRequest = PageRequest.of(page, 10);
        Page<Mission> missionPage = missionRepository.findAvailableMission(memberId, location, pageRequest);

        return MissionConverter.toMissionListDTOFromMission(missionPage);
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

    @Transactional
    public Void createMission(Long storeId, MissionReqDTO.CreateMission dto) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Mission mission = MissionConverter.toMission(store, dto);

        missionRepository.save(mission);
        return null;
    }

    @Transactional
    public BaseResDTO.Pagination<MissionResDTO.GetMission> getMissions(
            Long storeId,
            Integer pageSize,
            Integer pageNumber,
            String sort
    ) {

        Sort sortInfo;
        if(sort != null){
            sortInfo = Sort.by(sort);
        } else {
            sortInfo = Sort.by("id").descending();
        }

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

        Page<Mission> missionList = missionRepository.findAllByStore_Id(storeId, pageRequest);

        return MissionConverter.toPagination(
                missionList.map(MissionConverter::toGetMission).toList(),
                missionList.getNumber(),
                missionList.getSize()
        );
    }
}
