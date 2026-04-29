package umc.domain.member.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import umc.domain.member.service.MemberService;
import umc.domain.mission.dto.MissionResDTO;
import umc.global.apiPayload.ApiResponse;

@RestController
@RequestMapping("/api/home")
@RequiredArgsConstructor
public class HomeController {
	private final MemberService memberService;

	// 홈화면
	@GetMapping("")
	public ApiResponse<MissionResDTO.HomeViewDTO> getHomeInfo(
		@RequestParam(name = "regionName") String regionName) {

		return ApiResponse.onSuccess();
	}
}
