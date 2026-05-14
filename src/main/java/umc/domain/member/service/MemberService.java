package umc.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.category.entity.mapping.StoreCategory;
import umc.domain.category.repository.StoreCategoryRepository;
import umc.domain.member.converter.MemberConverter;
import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.entity.Member;
import umc.domain.member.exception.MemberException;
import umc.domain.member.exception.code.MemberErrorCode;
import umc.domain.member.repository.MemberRepository;
import umc.domain.mission.entity.Mission;
import umc.domain.mission.enums.MemberMissionStatus;
import umc.domain.mission.repository.MemberMissionRepository;
import umc.domain.mission.repository.MissionRepository;
import umc.domain.store.entity.Region;
import umc.domain.store.exception.StoreException;
import umc.domain.store.exception.code.StoreErrorCode;
import umc.domain.store.repository.RegionRepository;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final MissionRepository missionRepository;
    private final RegionRepository regionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final StoreCategoryRepository storeCategoryRepository;

    @Transactional(readOnly = true)
    public MemberResDTO.GetInfo getInfo(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberConverter.toGetInfo(member);
    }

    @Transactional(readOnly = true)
    public MemberResDTO.Home getHome(Long memberId, Long regionId, Integer page){
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        Region region = regionRepository.findById(regionId)
                .orElseThrow(() -> new StoreException((StoreErrorCode.REGION_NOT_FOUND)));

        Pageable pageable = PageRequest.of(page, 10);
        Page<Mission> missions = missionRepository.findHomeMissions(regionId, pageable);

        List<Long> storeIds = missions.getContent().stream()
                .map(mission -> mission.getStore().getId())
                .distinct()
                .toList();

        Map<Long, String> storeCategoryMap = Map.of();

        if(!storeIds.isEmpty()){
            List<StoreCategory> storeCategories =
                    storeCategoryRepository.findAllByStoreIdsWithFoodCategory(storeIds);

            storeCategoryMap = storeCategories.stream()
                    .collect(Collectors.groupingBy(
                            storeCategory -> storeCategory.getStore().getId(),
                            Collectors.mapping(
                                    storeCategory -> storeCategory.getFoodCategory().getName(),
                                    Collectors.joining(", ")
                            )
                    ));
        }

        int completedMissionCount = memberMissionRepository.countByMemberIdAndStatus(
                memberId,
                MemberMissionStatus.COMPLETED
        );

        return MemberConverter.toHome(member, region, missions, completedMissionCount, storeCategoryMap);
    }
}
