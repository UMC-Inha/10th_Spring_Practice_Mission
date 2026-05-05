package umc.domain.member.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

public class MemberResDTO {

	@Builder
	public record GetInfo(
		String name,
		String profileUrl,
		String email,
		String phoneNumber,
		Integer point
	) {}

	@Builder
	@Getter
	@NoArgsConstructor
	@AllArgsConstructor
	// 회원가입시 memberID와 생성시간
	public static class JoinResultDTO {
		Long memberId;
		LocalDateTime createdAt;
	}
}
