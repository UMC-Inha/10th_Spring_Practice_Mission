package umc.domain.member.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import umc.domain.food.entity.Food;
import umc.domain.food.repository.FoodRepository;
import umc.domain.member.converter.MemberConverter;
import umc.domain.member.dto.MemberRequestDTO;
import umc.domain.member.dto.MemberResponseDTO;
import umc.domain.member.entity.Member;
import umc.domain.member.entity.mapping.MemberMission;
import umc.domain.member.entity.mapping.MemberPreferFood;
import umc.domain.member.exception.MemberException;
import umc.domain.member.exception.code.MemberErrorCode;
import umc.domain.member.repository.MemberMissionRepository;
import umc.domain.member.repository.MemberPreferFoodRepository;
import umc.domain.member.repository.MemberRepository;
import umc.domain.mission.entity.Mission;
import umc.domain.mission.enums.MissionStatus;
import umc.domain.mission.repository.MissionRepository;
import umc.domain.region.entity.Region;
import umc.domain.region.exception.RegionException;
import umc.domain.region.exception.code.RegionErrorCode;
import umc.domain.region.repository.RegionRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final RegionRepository regionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;


    // 마이 페이지
    @Transactional
    public MemberResponseDTO.MyPageDTO getMyPage(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberConverter.toMyPageViewDTO(member);
    }

    // 홈 화면 조회
    @Transactional
    public MemberResponseDTO.HomeDTO getHome(Long memberId, String regionName, int page, int pageSize){

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Region region = regionRepository.findByRegionName(regionName)
                .orElseThrow(() -> new RegionException(RegionErrorCode.REGION_NOT_FOUND));

        Integer missionCompletedCount = memberMissionRepository.countCompletedMissionsByRegion(
                memberId, regionName, MissionStatus.COMPLETED
        );

        Pageable pageable = PageRequest.of(page, pageSize);
        Page<Mission> missionPage = missionRepository.findMissionsByRegion(member.getId(), regionName, pageable);

        return MemberConverter.toHomeDTO(member, region, missionCompletedCount, missionPage);
    }
}
