package umc.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.exception.code.MissionSuccessCode;
import umc.global.apiPayload.ApiResponse;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {

    @GetMapping("/v1/missions/me")
    public ApiResponse<MissionResDTO.MissionList> getMissions(
            @RequestParam boolean is_completed
    ){
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_LIST_GET_SUCCESS, null);
    }

}
