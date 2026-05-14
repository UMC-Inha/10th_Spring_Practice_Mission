package umc.domain.mission.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.mission.dto.MissionReqDTO;
import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.enums.MemberMissionStatus;
import umc.domain.mission.exception.code.MissionSuccessCode;
import umc.domain.mission.service.MissionService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.BaseSuccessCode;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/missions")
    public ApiResponse<MissionResDTO.GetMissions> getMissions(
            @RequestParam List<MemberMissionStatus> status,
            @RequestParam(defaultValue = "0") Integer page
    ) {
        Long memberId = 1L; // TODO: memberId from access token
        BaseSuccessCode code = MissionSuccessCode.MISSION_VIEW;
        MissionResDTO.GetMissions response = missionService.getMissions(memberId, status, page);
        return ApiResponse.onSuccess(code, response);
    }

    @PatchMapping("/member-missions/{memberMissionId}")
    public ApiResponse<MissionResDTO.UpdateMissionStatus>
    updateMissionsStatus(
            @PathVariable Long memberMissionId,
            @RequestBody MissionReqDTO.UpdateMissionStatus request
            ) {
        BaseSuccessCode code = MissionSuccessCode.STATUS_UPDATED;
        return ApiResponse.onSuccess(code, null);
    }
}
