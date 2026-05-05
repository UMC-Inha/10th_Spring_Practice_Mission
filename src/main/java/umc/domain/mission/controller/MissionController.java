package umc.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.mission.dto.MissionReqDTO;
import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.exception.code.MissionSuccessCode;
import umc.domain.mission.service.MissionService;
import umc.global.apiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/v1/missions/me/{isCompleted}/{memberId}")
    public ApiResponse<MissionResDTO.MissionList> getMissions(
            @RequestParam boolean is_completed,
            @RequestParam Long memberId
    ){
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_LIST_GET_SUCCESS, missionService.getMissions(memberId, is_completed));
    }

    @PatchMapping("/v1/missions/{missionId}")
    public ApiResponse<MissionResDTO.MissionDTO> updateMission(
            @PathVariable Long missionId,
            @RequestBody MissionReqDTO.MissionStatusUpdate req
    ){
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_UPDATE, null);
    }

}
