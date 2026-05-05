package umc.domain.mission.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import umc.domain.member.entity.Member;
import umc.domain.member.exception.MemberException;
import umc.domain.member.exception.code.MemberErrorCode;
import umc.domain.member.repository.MemberRepository;
import umc.domain.mission.converter.MissionConverter;
import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.entity.mapping.MemberMission;
import umc.domain.mission.enums.MissionStatus;
import umc.domain.mission.repository.MemberMissionRepository;

@Service
@RequiredArgsConstructor
public class MissionQueryService {

	private final MemberMissionRepository memberMissionRepository;
	private final MemberRepository memberRepository;
	private final MissionConverter missionConverter;

	@Transactional(readOnly = true)
	public MissionResDTO.MissionListDTO getMissions(Long memberId, MissionStatus status, Integer page) {

		Member member =  memberRepository.findById(memberId)
			.orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));


		Pageable pageable = PageRequest.of(page, 10);

		Page<MemberMission> memberMissions = memberMissionRepository.findMyMissions(memberId, status, pageable);

		return missionConverter.toMissionListDTO(memberMissions);
	}
}
