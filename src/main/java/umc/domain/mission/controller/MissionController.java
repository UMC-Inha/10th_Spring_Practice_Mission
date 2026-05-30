package umc.domain.mission.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.domain.mission.dto.MissionReqDTO;
import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.exception.code.MissionSuccessCode;
import umc.domain.mission.service.MissionService;
import umc.global.apiPayload.ApiResponse;
import umc.global.dto.PageResDTO;
import umc.global.security.entity.AuthMember;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/v1/missions/me")
    public ApiResponse<PageResDTO.Pagination<MissionResDTO.MissionDTO>> getMissions(
            @AuthenticationPrincipal AuthMember member,
            @RequestParam boolean isCompleted,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort
    ){
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_LIST_GET_SUCCESS, missionService.getMissions(member, isCompleted, pageSize, pageNumber, sort));
    }

    @PatchMapping("/v1/missions")
    public ApiResponse<MissionResDTO.MissionDTO> updateMission(
            @PathVariable @NotNull Long missionId,
            @RequestBody @Valid MissionReqDTO.MissionStatusUpdate req
    ){
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_UPDATE, null);
    }

}
