package umc.domain.mission.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import umc.domain.member.entity.mapping.MemberMission;
import umc.domain.member.repository.MemberMissionRepository;
import umc.domain.mission.converter.MissionConverter;
import umc.domain.mission.dto.MissionRequestDTO;
import umc.domain.mission.dto.MissionResponseDTO;
import umc.domain.mission.entity.Mission;
import umc.domain.mission.enums.MissionStatus;
import umc.domain.mission.repository.MissionRepository;
import umc.domain.store.entity.Store;
import umc.domain.store.exception.StoreException;
import umc.domain.store.exception.code.StoreErrorCode;
import umc.domain.store.repository.StoreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;

    // 내 미션 목록 조회 (status 필터링, 페이징)
    @Transactional
    public MissionResponseDTO.MissionList getMissions(Long memberId, MissionStatus status, Integer page, Integer size) {
        Page<MemberMission> memberMissions = memberMissionRepository
                .findByMemberIdAndStatus(memberId, status, PageRequest.of(page, size));
        return MissionConverter.toMissionList(memberMissions);
    }

    // 홈 화면 미션 목록 조회 (지역 필터링, 페이징)
    @Transactional
    public MissionResponseDTO.HomeMissionList getHomeMissions(String regionName, Integer page) {
        Long memberId = 1L;
        Page<Mission> missions = missionRepository
                .findMissionsByRegion(memberId, regionName, PageRequest.of(page, 10));
        Integer completedCount = memberMissionRepository
                .countCompletedMissionsByRegion(memberId, regionName, MissionStatus.COMPLETED);
        return MissionConverter.toHomeMissionList(regionName, 999999, completedCount, missions.getContent());
    }

    // 가게 미션 생성
    @Transactional
    public Void createMission(
            Long storeId,
            MissionRequestDTO.CreateMission dto
    ) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Mission mission = MissionConverter.toMission(store, dto);

        missionRepository.save(mission);
        return null;

    }

    // 가게 내 미션 목록 조회
    @Transactional
    public MissionResponseDTO.Pagination<MissionResponseDTO.GetMission> getStoreMissions(
            Long storeId,
            Integer pageSize,
            Integer pageNumber,
            String sort
    ) {

        // 정렬 정보 생성
        Sort sortInfo;
        if (sort != null) {
            sortInfo = Sort.by(sort);
        } else {
            sortInfo = Sort.by("id").descending();
        }

        // 페이지 정보들을 PageRequest로 (PageRequest = Pageable을 구현한 객체)
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);

        Page<Mission> missionList = missionRepository.findAllByStore_Id(storeId, pageRequest);

        // 미션들 응답 DTO 포장
        return MissionConverter.toPagination(
                missionList.map(MissionConverter::toGetMission).toList(),
                missionList.getNumber(),
                missionList.getSize()
        );
    }
}
