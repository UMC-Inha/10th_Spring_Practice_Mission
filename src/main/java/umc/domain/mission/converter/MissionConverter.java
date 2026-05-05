package umc.domain.mission.converter;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Page;
import org.springframework.stereotype.Component;

import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.entity.Mission;
import umc.domain.mission.entity.mapping.MemberMission;
import umc.domain.store.entity.Store;

@Component
public class MissionConverter {

	public MissionResDTO.MissionDetailDTO toMissionDetailDTO(MemberMission memberMission) {

		Mission mission = memberMission.getMission();
		Store store = mission.getStore();

		return MissionResDTO.MissionDetailDTO.builder()
			.memberMissionId(memberMission.getId())
			.missionId(mission.getId())
			.storeName(store.getName())
			.point(mission.getSuccessPoint())
			.missionContent(mission.getMissionCondition())
			.status(memberMission.getStatus().toString())
			.build();
	}

	public MissionResDTO.MissionListDTO toMissionListDTO(Page<MemberMission> page) {
		List<MissionResDTO.MissionDetailDTO> missions = page.getContent()
			.stream()
			.map(this::toMissionDetailDTO)
			.collect(Collectors.toList());

		return MissionResDTO.MissionListDTO.builder()
			.missions(missions)
			.hasNext(page.hasNext())
			.build();
	}
}
