package umc.domain.mission.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MissionStatus {

	IN_PROGRESS("진행중"),
	SUCCESS("진행완료");

	private final String label;
}
