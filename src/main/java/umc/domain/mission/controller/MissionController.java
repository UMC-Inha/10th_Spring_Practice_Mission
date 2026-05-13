package umc.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.mission.dto.MissionResponseDTO;
import umc.domain.mission.enums.MissionStatus;
import umc.domain.mission.exception.code.MissionSuccessCode;
import umc.domain.mission.service.MissionService;
import umc.global.apiPayload.ApiResponse;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    @GetMapping("/missions")
    public ApiResponse<MissionResponseDTO.MissionList> getMissions(
            @RequestParam MissionStatus status,
            @RequestParam(value = "page", defaultValue = "0") Integer page
    ) {
        Long memberId = 1L;
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_LIST_OK,
                missionService.getMissions(memberId, status, page));
    }

}
