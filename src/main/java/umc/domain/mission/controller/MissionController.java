package umc.domain.mission.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.exception.code.MissionSuccessCode;
import umc.domain.mission.service.MissionService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.BaseSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {
/*
    private final MissionService missionService;

    //미션 목록 조회
    @GetMapping("/userMissions")
    @Operation(summary = "미션 목록 조회")
    public ApiResponse<MissionResDTO.MissionPreviewListDTO> getMyMissions(
            @RequestParam(name = "status") String status,
            @RequestParam(name = "cursor", required = false) Long cursor
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        MissionResDTO.MissionPreviewListDTO result = missionService.getMyMissions(memberId, status, cursor);
        return ApiResponse.onSuccess(code, result);
    }

    @PatchMapping("/missions/{userMissionId}")
    @Operation(summary = "미션 완료 처리")
    public ApiResponse<MissionResDTO.ChangeStatusDTO> completeMission(
            @PathVariable(name = "userMissionId") Long userMissionId
    ){
        BaseSuccessCode code = MissionSuccessCode.MISSION_OK;
        MissionResDTO.ChangeStatusDTO result = missionService.completeMission(memberId, userMissionId);
        return ApiResponse.onSuccess(code, result);
    }

 */
}
