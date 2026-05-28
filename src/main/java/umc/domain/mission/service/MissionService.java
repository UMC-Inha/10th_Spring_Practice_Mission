package umc.domain.mission.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import umc.domain.mission.converter.MissionConverter;
import umc.domain.mission.dto.MissionReqDTO;
import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.entity.Mission;
import umc.domain.mission.repository.MissionRepository;
import umc.domain.store.entity.Store;
import umc.domain.store.exception.StoreException;
import umc.domain.store.exception.code.StoreErrorCode;
import umc.domain.store.repository.StoreRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MissionService {

    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    // 가게 미션 생성
    @Transactional
    public MissionResDTO.GetCreateMissionDTO createMission(
            Long storeId,
            MissionReqDTO.CreateMissionDTO dto
    ){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));
        Mission mission = MissionConverter.toPutMission(store, dto);
        return MissionConverter.toGetCreateMission(missionRepository.save(mission));
    }

    // 가게 미션 조회
    public List<MissionResDTO.GetMissionDTO> getMissions(
            Long storeId,
            Integer pageSize,
            Integer pageNumber,
            String sort
    ){
        Sort sortInfo;
        if(sort != null){
            if(sort.equalsIgnoreCase("asc")){
                sortInfo = Sort.by("id").ascending();
            } else if(sort.equalsIgnoreCase("desc")){
                sortInfo = Sort.by("id").descending();
            } else {
                sortInfo = Sort.by(sort);
            }
        } else {
            sortInfo = Sort.by("id").descending();
        }

        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sortInfo);
        Page<Mission> missionList = missionRepository.findAllByStoreId(storeId, pageRequest);
        return missionList.map(MissionConverter::toGetMission).getContent();
    }
}