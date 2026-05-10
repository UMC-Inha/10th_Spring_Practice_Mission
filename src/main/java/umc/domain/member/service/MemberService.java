package umc.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.member.converter.MemberConverter;
import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.entity.Member;
import umc.domain.member.exception.MemberException;
import umc.domain.member.exception.code.MemberErrorCode;
import umc.domain.member.repository.MemberRepository;
import umc.domain.mission.converter.MissionConverter;
import umc.domain.mission.entity.Mission;
import umc.domain.mission.enums.MissionStatus;
import umc.domain.mission.repository.MemberMissionRepository;
import umc.domain.mission.repository.MissionRepository;
import umc.domain.region.entity.Region;
import umc.domain.region.exception.RegionException;
import umc.domain.region.exception.code.RegionErrorCode;
import umc.domain.region.repository.RegionRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final RegionRepository regionRepository;
    private final MemberMissionRepository memberMissionRepository;
    private final MissionRepository missionRepository;

    public MemberResDTO.MyPageViewDTO getMyPage(Long memberId) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        return MemberConverter.toMyPageViewDTO(member);
    }

    public MemberResDTO.HomeViewDTO getHome(
            Long memberId,
            String regionName,
            LocalDate cursorDueDate,
            Long cursorId,
            int pageSize
    ) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        Region region = regionRepository.findByRegionName(regionName)
                .orElseThrow(() -> new RegionException(RegionErrorCode.NOT_FOUND_BY_NAME));

        Integer missionCount = memberMissionRepository.countMissionsByRegion(
                memberId,
                region.getId(),
                MissionStatus.COMPLETE
        );

        if (cursorDueDate == null || cursorId == null){
            cursorDueDate = LocalDate.of(1900,1,1);
            cursorId = -1L;
        }

        List<Mission> missions = missionRepository.findChallengeableMissionsByRegion(
                memberId,
                region.getId(),
                cursorDueDate,
                cursorId,
                pageSize + 1
        );

        boolean hasNextPage = missions.size() > pageSize;
        List<Mission> results = new ArrayList<>(missions.subList(0, hasNextPage ? pageSize : missions.size()));

        return MemberConverter.toHomeViewDTO(member, region, missionCount, 10, results);
    }
}
