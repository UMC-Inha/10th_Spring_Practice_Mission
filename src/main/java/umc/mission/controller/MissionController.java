package umc.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.apiPayload.ApiResponse;
import umc.apiPayload.code.BaseSuccessCode;
import umc.mission.dto.MissionResDTO;
import umc.mission.exception.code.MissionSuccessCode;
import umc.mission.service.MissionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {
    private final MissionService missionService;

    @GetMapping("/v1/users/me/missions")
    public ApiResponse<MissionResDTO.MissionListDTO> getMyMissions(
            @RequestParam(name = "isComplete") Boolean isComplete,
            @RequestParam(name = "page") Integer page
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.USER_MISSION_OK, missionService.getMyMissions(isComplete, page));
    }

    @PatchMapping("/v1/users/user-missions/{userMissionId}")
    public ApiResponse<MissionResDTO.MissionCompleteResultDTO> completeMission(
            @PathVariable(name = "userMissionId") Long userMissionId
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.COMPLETE_OK, missionService.completeMissions(userMissionId));
    }
}
