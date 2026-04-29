package umc.domain.member.dto;

import java.util.List;

import lombok.Getter;

public class MemberReqDTO {

	// 마이페이지
	public record GetInfo(
		Long id
	) {}

	// 회원가입
	@Getter
	public static class JoinDTO {
		List<TermDTO> terms; // 약관 동의 목록
		String name;
		String gender;
		String birth;
		String address;
		String email;
		List<Long> favoriteFoods; // 선택한 선호 음식
	}

	@Getter
	public static class TermDTO {
		Long termId;
		Boolean isAgree;
	}
}
