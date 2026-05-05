package umc.domain.mission.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import umc.domain.mission.dto.MissionResDTO;
import umc.global.apiPayload.ApiResponse;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor

public class MissionController {
	// 미션 목록 조회 (진행중, 완료)
	@GetMapping("/mypages/missions")
	public ApiResponse<MissionResDTO.MissionListDTO> getMyMissions(
		@RequestParam(name = "regionName") String regionName,
		@RequestParam(name = "status") String status) {

		// return ApiResponse.onSuccess();
	}

	// 미션 성공 누르기 (도전/완료)
	@PostMapping("/missions/{missionId}")
	public ApiResponse<Long> completeMission(@PathVariable(name = "missionId") Long missionId) {
		// return ApiResponse.onSuccess();
	}
}
