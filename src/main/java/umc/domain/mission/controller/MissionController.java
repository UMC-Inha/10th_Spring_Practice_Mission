package umc.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.mission.dto.MissionReqDTO;
import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.enums.MissionStatus;
import umc.domain.mission.exception.code.MissionSuccessCode;
import umc.global.apiPayload.ApiResponse;

import umc.domain.mission.service.MissionService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @GetMapping
    public ApiResponse<MissionResDTO.MissionListDto> getMissions(
            @RequestParam List<MissionStatus> missionStatus,
            @RequestParam(required = false) LocalDate cursorDueDate,
            @RequestParam(required = false) Long cursorId,
            @RequestParam(defaultValue = "10") int pageSize
    ){
        return ApiResponse.onSuccess(MissionSuccessCode.MISSIONS_VIEW,
                missionService.getMissions(1L, missionStatus, cursorDueDate, cursorId, pageSize));
    }

    @PatchMapping("/{memberMissionId}")
    public ApiResponse<MissionResDTO.MissionStatusUpdateDTO> updateMissionStatus(
            @PathVariable Long memberMissionId,
            @RequestBody MissionReqDTO.MissionStatusUpdateDTO reqDto
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_STATUS_UPDATED, null);
    }
}
