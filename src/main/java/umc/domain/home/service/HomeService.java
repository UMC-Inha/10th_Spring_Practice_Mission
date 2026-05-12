package umc.domain.home.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import umc.domain.home.converter.HomeConverter;
import umc.domain.home.dto.HomeResDTO;
import umc.domain.member.entity.Member;
import umc.domain.member.exception.MemberException;
import umc.domain.member.exception.code.MemberErrorCode;
import umc.domain.member.repository.MemberRepository;
import umc.domain.mission.entity.Mission;
import umc.domain.mission.entity.mapping.MemberMission;
import umc.domain.mission.repository.MemberMissionRepository;
import umc.domain.mission.repository.MissionRepository;
import umc.domain.store.entity.Region;
import umc.domain.store.entity.Store;
import umc.domain.store.exception.StoreException;
import umc.domain.store.exception.code.RegionErrorCode;
import umc.domain.store.exception.code.StoreErrorCode;
import umc.domain.store.repository.RegionRepository;
import umc.domain.store.repository.StoreRepository;

import java.util.List;

@AllArgsConstructor
@Service
public class HomeService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final StoreRepository storeRepository;
    private final RegionRepository regionRepository;
    private final MemberMissionRepository memberMissionRepository;

    public HomeResDTO.HomeDTO getHome(Long memberId, String region) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                ()->new MemberException(MemberErrorCode.MEMBER_NOT_FOUND)
        );

        Region r = regionRepository.findByName(region).orElseThrow(
                ()-> new StoreException(RegionErrorCode.REGION_NOT_FOUND)
        );

        Integer missionCount = missionRepository.countByRegion(r);
        Integer missionSuccessCount = memberMissionRepository.countCompletedMissionsByRegion(memberId, r);
        List<Mission> unstartedMissionList = missionRepository.findUnstartedMissions(r);

        Integer currentPoint = member.getPoint();

        return HomeConverter.toGetHome(region, missionCount, missionSuccessCount, currentPoint, unstartedMissionList);
    }
}
