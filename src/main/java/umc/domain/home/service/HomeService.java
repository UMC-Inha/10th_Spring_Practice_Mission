package umc.domain.home.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.home.converter.HomeConverter;
import umc.domain.home.dto.HomeResDTO;
import umc.domain.member.entity.Member;

import umc.domain.mission.entity.Mission;
import umc.domain.mission.repository.MemberMissionRepository;
import umc.domain.mission.repository.MissionRepository;
import umc.domain.store.entity.Region;
import umc.domain.store.exception.StoreException;
import umc.domain.store.exception.code.RegionErrorCode;
import umc.domain.store.repository.RegionRepository;
import umc.global.security.entity.AuthMember;

import java.util.List;

@AllArgsConstructor
@Service
public class HomeService {

    private final MissionRepository missionRepository;
    private final RegionRepository regionRepository;
    private final MemberMissionRepository memberMissionRepository;

    @Transactional(readOnly = true)
    public HomeResDTO.HomeDTO getHome(AuthMember authMember, String region) {
        Member member = authMember.getMember();

        Region r = regionRepository.findByName(region).orElseThrow(
                ()-> new StoreException(RegionErrorCode.REGION_NOT_FOUND)
        );

        Integer missionCount = missionRepository.countByRegion(r);
        Integer missionSuccessCount = memberMissionRepository.countCompletedMissionsByRegion(member.getId(), r);
        List<Mission> unstartedMissionList = missionRepository.findUnstartedMissions(r);

        Integer currentPoint = member.getPoint();

        return HomeConverter.toGetHome(region, missionCount, missionSuccessCount, currentPoint, unstartedMissionList);
    }
}
