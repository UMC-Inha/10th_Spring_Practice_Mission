package com.example.umc10th.domain.mission.service;

import com.example.umc10th.domain.mission.converter.MemberMissionConverter;
import com.example.umc10th.domain.mission.dto.MemberMissionListResponseDto;
import com.example.umc10th.domain.mission.dto.MemberMissionOffsetPageResponseDto;
import com.example.umc10th.domain.mission.entity.MemberMission;
import com.example.umc10th.domain.mission.enums.MemberMissionStatus;
import com.example.umc10th.domain.mission.repository.MemberMissionRepository;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MemberMissionServiceImpl implements MemberMissionService {

	private final MemberMissionRepository memberMissionRepository;
	private final MemberMissionConverter memberMissionConverter;

	public MemberMissionServiceImpl(
		MemberMissionRepository memberMissionRepository,
		MemberMissionConverter memberMissionConverter
	) {
		this.memberMissionRepository = memberMissionRepository;
		this.memberMissionConverter = memberMissionConverter;
	}

	@Override
	@Transactional(readOnly = true)
	public MemberMissionListResponseDto getMemberMissions(
		Long memberId,
		MemberMissionStatus status,
		Long cursor,
		Integer size
	) {
		List<MemberMissionStatus> statuses = toSearchStatuses(status);
		List<MemberMission> memberMissions = memberMissionRepository.findByMemberAndStatusesWithCursor(
			memberId,
			statuses,
			cursor,
			PageRequest.of(0, size + 1)
		);

		boolean hasNext = memberMissions.size() > size;
		List<MemberMission> responseMissions = hasNext ? memberMissions.subList(0, size) : memberMissions;
		Long nextCursor = hasNext && !responseMissions.isEmpty()
			? responseMissions.get(responseMissions.size() - 1).getId()
			: null;

		return new MemberMissionListResponseDto(
			status,
			responseMissions.stream()
				.map(memberMissionConverter::toSummary)
				.toList(),
			size,
			nextCursor,
			hasNext
		);
	}

	@Override
	@Transactional(readOnly = true)
	public MemberMissionOffsetPageResponseDto getInProgressMemberMissions(
		Long memberId,
		Integer pageNumber,
		Integer pageSize
	) {
		List<MemberMissionStatus> statuses = toSearchStatuses(MemberMissionStatus.IN_PROGRESS);
		Page<Long> page = memberMissionRepository.findIdsByMemberAndStatusesWithOffset(
			memberId,
			statuses,
			PageRequest.of(pageNumber, pageSize)
		);
		List<MemberMission> memberMissions = findMemberMissionsInPageOrder(page.getContent());

		return new MemberMissionOffsetPageResponseDto(
			memberMissions.stream()
				.map(memberMissionConverter::toSummary)
				.toList(),
			page.getNumber(),
			page.getSize(),
			page.getTotalElements(),
			page.getTotalPages(),
			page.isFirst(),
			page.isLast()
		);
	}

	private List<MemberMission> findMemberMissionsInPageOrder(List<Long> memberMissionIds) {
		if (memberMissionIds.isEmpty()) {
			return List.of();
		}

		List<MemberMission> memberMissions = memberMissionRepository.findAllWithMissionAndStoreByIdIn(memberMissionIds);
		Map<Long, MemberMission> memberMissionMap = memberMissions.stream()
			.collect(Collectors.toMap(
				MemberMission::getId,
				memberMission -> memberMission,
				(left, right) -> left
			));

		return memberMissionIds.stream()
			.map(memberMissionMap::get)
			.toList();
	}

	private List<MemberMissionStatus> toSearchStatuses(MemberMissionStatus status) {
		if (status == MemberMissionStatus.IN_PROGRESS) {
			List<MemberMissionStatus> statuses = new ArrayList<>();
			statuses.add(MemberMissionStatus.IN_PROGRESS);
			statuses.add(MemberMissionStatus.SUCCESS_REQUESTED);
			return statuses;
		}

		return List.of(status);
	}
}
