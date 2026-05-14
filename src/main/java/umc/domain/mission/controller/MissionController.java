package umc.domain.mission.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;
import umc.domain.mission.dto.MissionRequestDTO;
import umc.domain.mission.dto.MissionResponseDTO;
import umc.domain.mission.enums.MissionStatus;
import umc.domain.mission.exception.code.MissionSuccessCode;
import umc.domain.mission.service.MissionService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.BaseSuccessCode;

import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class MissionController {

    private final MissionService missionService;

    // 내 미션 목록 조회 (status 필터링)
    @GetMapping("/missions")
    public ApiResponse<MissionResponseDTO.MissionList> getMissions(
            @RequestParam MissionStatus status,
            @RequestParam(value = "page", defaultValue = "0") Integer page
    ) {
        Long memberId = 1L;
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_LIST_OK,
                missionService.getMissions(memberId, status, page));
    }

    // 가게 미션 생성
    @PostMapping("/stores/{storeId}/missions")
    public ApiResponse<Void> createMission(
            @PathVariable Long storeId,
            @RequestBody MissionRequestDTO.CreateMission dto
    ) {
        BaseSuccessCode code = MissionSuccessCode.CREATED;
        return ApiResponse.onSuccess(code, missionService.createMission(storeId, dto));
    }

    // 가게 내 미션 목록 조회
    @GetMapping("/stores/{storeId}/missions")
    public ApiResponse<MissionResponseDTO.Pagination<MissionResponseDTO.GetMission>> getStoreMissions(
            @PathVariable Long storeId,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort
    ) {
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getStoreMissions(storeId, pageSize, pageNumber, sort));
    }
}
