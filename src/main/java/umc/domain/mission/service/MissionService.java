package umc.domain.mission.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.member.entity.Member;
import umc.domain.member.exception.MemberException;
import umc.domain.member.exception.code.MemberErrorCode;
import umc.domain.member.repository.MemberRepository;
import umc.domain.mission.converter.MissionConverter;
import umc.domain.mission.dto.MissionReqDTO;
import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.entity.mapping.MemberMission;
import umc.domain.mission.enums.MissionStatus;
import umc.domain.mission.repository.MemberMissionRepository;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MissionService {

    private final MemberMissionRepository memberMissionRepository;
    private final MemberRepository memberRepository;

    public MissionResDTO.CursorPage getMissions(
            Long memberId,
            List<MissionStatus> statuses,
            LocalDate cursorDueDate,
            Long cursorId,
            int pageSize
    ) {
        List<MemberMission> memberMissions;

        if (cursorDueDate == null || cursorId == null){
            cursorDueDate = LocalDate.of(1900,1,1);
            cursorId = -1L;
        }

        memberMissions = memberMissionRepository.findMemberMissionsUsingCursor(
                memberId,
                statuses,
                cursorDueDate,
                cursorId,
                pageSize+1
        );

        boolean hasNextPage = memberMissions.size() > pageSize;
        List<MemberMission> results = new ArrayList<>(
                memberMissions.subList(0, hasNextPage ? pageSize : memberMissions.size())
        );

        return MissionConverter.toCursorPage(results, hasNextPage);
    }

    public MissionResDTO.OffsetPage getMissionsUsingOffset(
            MissionReqDTO.MissionViewDTO reqDto,
            List<MissionStatus> statuses,
            Pageable pageable
    ) {
        Member member = memberRepository.findById(reqDto.memberId())
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        Page<MemberMission> memberMissions = memberMissionRepository.findMemberMissionsUsingOffset(
                member.getId(),
                statuses,
                pageable
        );

        return MissionConverter.toOffsetPage(memberMissions);
    }
}
