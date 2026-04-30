package umc.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.mission.dto.MissionReqDTO;
import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.enums.MissionStatus;
import umc.domain.mission.exception.code.MissionSuccessCode;
import umc.global.apiPayload.ApiResponse;

import java.util.List;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
public class MissionController {

    @GetMapping
    public ApiResponse<MissionResDTO.MissionListDto> getMissions(
            @RequestParam List<MissionStatus> missionStatus
    ){
        return ApiResponse.onSuccess(MissionSuccessCode.MISSIONS_VIEW, null);
    }

    @PatchMapping("/{memberMissionId}")
    public ApiResponse<MissionResDTO.MissionStatusUpdateDTO> updateMissionStatus(
            @PathVariable Long memberMissionId,
            @RequestBody MissionReqDTO.MissionStatusUpdateDTO reqDto
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_STATUS_UPDATED, null);
    }
}
