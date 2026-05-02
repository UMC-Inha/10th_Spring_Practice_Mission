package umc.domain.mission.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.mission.dto.MissionReqDTO;
import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.enums.MemberMissionStatus;
import umc.domain.mission.exception.code.MissionSuccessCode;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.BaseSuccessCode;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    @GetMapping("/missions")
    public ApiResponse<MissionResDTO.GetMissions> getMissions(
            @RequestParam List<MemberMissionStatus> status
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, null);
    }

    @PatchMapping("/missions/{memberMissionId}")
    public ApiResponse<MissionResDTO.UpdateMissionStatus>
    updateMissionsStatus(
            @PathVariable Long memberMissionId,
            @RequestBody MissionReqDTO.UpdateMissionStatus request
            ) {
        BaseSuccessCode code = MissionSuccessCode.STATUS_UPDATED;
        return ApiResponse.onSuccess(code, null);
    }
}
