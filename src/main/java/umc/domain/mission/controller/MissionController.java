package umc.domain.mission.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.domain.mission.dto.MissionReqDTO;
import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.exception.code.MissionSuccessCode;
import umc.domain.mission.service.MissionService;
import umc.global.apiPayload.ApiResponse;
import umc.global.dto.PageResDTO;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/v1/missions/me/{memberId}")
    public ApiResponse<PageResDTO.Pagination<MissionResDTO.MissionDTO>> getMissions(
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
            @PathVariable @NotNull Long missionId,
            @RequestBody @Valid MissionReqDTO.MissionStatusUpdate req
    ){
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_UPDATE, null);
    }

}
