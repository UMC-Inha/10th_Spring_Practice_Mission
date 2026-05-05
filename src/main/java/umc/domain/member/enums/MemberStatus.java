package umc.domain.member.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum MemberStatus {

	ACTIVE("활동"),
	INACTIVE("비활동");

	private final String label;
}
