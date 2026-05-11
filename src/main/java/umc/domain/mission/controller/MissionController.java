package umc.domain.mission.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.enums.MissionStatus;
import umc.domain.mission.exception.code.MissionSuccessCode;
import umc.domain.mission.service.MissionQueryService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.GeneralSuccessCode;

@RestController
@RequestMapping("/api/v1")
@RequiredArgsConstructor

public class MissionController {
	// 미션 목록 조회 (진행중, 완료)
	private final MissionQueryService missionService;

	// 미션 목록 조회 (진행중, 완료)
	@GetMapping("/members/{memberId}/missions")
	public ApiResponse<MissionResDTO.MissionListDTO> getMyMissions(
		@PathVariable(name = "memberId") Long memberId,
		@RequestParam(name = "status") MissionStatus status,
		@RequestParam(defaultValue = "0") Integer page) {

		MissionResDTO.MissionListDTO result = missionService.getMissions(memberId, status, page);

		return ApiResponse.onSuccess(MissionSuccessCode.MISSION_FOUND, result);
	}


	// 미션 성공 누르기 (도전/완료)
	@PostMapping("/missions/{missionId}")
	public ApiResponse<Long> completeMission(@PathVariable(name = "missionId") Long missionId) {
		return ApiResponse.onSuccess(GeneralSuccessCode.OK, null);
	}
}
