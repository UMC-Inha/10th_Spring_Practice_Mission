package umc.domain.member.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Term {

	REQUIRED("필수"),
	OPTIONAL("선택");

	private final String label;
}
