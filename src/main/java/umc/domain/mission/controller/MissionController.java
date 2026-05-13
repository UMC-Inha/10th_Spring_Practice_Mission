package umc.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
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

    @GetMapping("/v1/missions/me/{memberId}")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.MissionDTO>> getMissions(
            @RequestParam boolean isCompleted,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort,
            @PathVariable Long memberId
    ){
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_LIST_GET_SUCCESS, missionService.getMissions(memberId, isCompleted, pageSize, pageNumber, sort));
    }

    @PatchMapping("/v1/missions/{missionId}")
    public ApiResponse<MissionResDTO.MissionDTO> updateMission(
            @PathVariable Long missionId,
            @RequestBody MissionReqDTO.MissionStatusUpdate req
    ){
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_UPDATE, null);
    }

}
