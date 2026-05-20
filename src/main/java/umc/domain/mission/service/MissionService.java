package umc.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.mission.converter.MissionConverter;
import umc.domain.mission.dto.MissionReqDTO;
import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.entity.Mission;
import umc.domain.mission.entity.mapping.MemberMission;
import umc.domain.mission.enums.MemberMissionStatus;
import umc.domain.mission.exception.MissionException;
import umc.domain.mission.exception.code.MissionErrorCode;
import umc.domain.mission.repository.MemberMissionRepository;
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
    private final StoreRepository storeRepository;
    private final MissionRepository missionRepository;

    @Transactional(readOnly = true)
    public MissionResDTO.GetMissions getMissions(
            Long memberId,
            List<MemberMissionStatus> statuses,
            Integer page
    ){
        Pageable pageable = PageRequest.of(page, 10);

        Page<MemberMission> memberMissions =
                memberMissionRepository.findMyMissions(memberId, statuses, pageable);

        return MissionConverter.toGetMemberMissions(memberMissions);
    }

    @Transactional
    public Void createMission(
            Long storeId,
            MissionReqDTO.CreateMission dto
    ){
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Mission mission = MissionConverter.toMission(store, dto);

        missionRepository.save(mission);
        return null;
    }

    /*
    // 오프셋 페이징
    @Transactional(readOnly = true)
    public MissionResDTO.Pagination<MissionResDTO.GetMission> getMissionsByStoreId(
            Long storeId,
            Integer pageSize,
            Integer pageNumber,
            String sort
    ){

        Sort sortInfo;
        if(sort != null){
            sortInfo = Sort.by(sort);
        }
        else{
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
     */

    // 진행중인 미션 조회용 service
    @Transactional(readOnly = true)
    public MissionResDTO.PaginationOffset<MissionResDTO.MissionPreview> getMyMissionsInProgressByMemberId(
            Long memberId,
            Integer pageSize,
            Integer pageNumber
    ) {
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize);

        Page<MemberMission> myMissionList =
                memberMissionRepository.findMyMissions(
                        memberId,
                        List.of(MemberMissionStatus.IN_PROGRESS),
                        pageRequest
        );

        return MissionConverter.toPaginationOffset(
                myMissionList.map(MissionConverter::toGetMemberMission).toList(),
                myMissionList.getNumber(),
                myMissionList.getSize()
        );
    }

    // 커서 페이징
    @Transactional(readOnly = true)
    public MissionResDTO.Pagination<MissionResDTO.GetMission> getMissionsByStoreId(
            Long storeId,
            Integer pageSize,
            String cursor,
            String query
    ){

        PageRequest pageRequest = PageRequest.of(0, pageSize);

        long idCursor;
        Slice<Mission> missionList;
        String nextCursor;

        if(!cursor.equals("-1")){

            String[] cursorSplit = cursor.split(":");
            switch(query.toLowerCase()){
                case "id":

                    Long prevCursor = Long.parseLong(cursorSplit[0]);
                    idCursor = Long.parseLong(cursorSplit[1]);

                    missionList = missionRepository.findMissionsByStore_IdAndIdLessThanOrderByIdDesc(
                            storeId,
                            idCursor,
                            pageRequest
                    );
                    break;
                default:
                    throw new MissionException(MissionErrorCode.QUERY_NOT_VALID);
            }
        }
        else{
            missionList = missionRepository.findMissionsByStore_IdOrderByIdDesc(storeId, pageRequest);
        }

        List<Mission> content = missionList.getContent();

        // 빈 페이지 응답
        if(content.isEmpty()){
            return MissionConverter.toPagination(
                    List.of(),
                    false,
                    null,
                    0
            );
        }

        Mission lastMission = content.get(content.size() -1 );
        nextCursor = lastMission.getId() + ":" + lastMission.getId();

        return MissionConverter.toPagination(
                missionList.map(MissionConverter::toGetMission).toList(),
                missionList.hasNext(),
                nextCursor,
                missionList.getSize()
        );
    }
}
