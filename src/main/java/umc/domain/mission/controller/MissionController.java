package umc.domain.mission.controller;


import jakarta.validation.Valid;
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
            @RequestBody @Valid MissionReqDTO.UpdateMissionStatus request
            ) {
        BaseSuccessCode code = MissionSuccessCode.STATUS_UPDATED;
        return ApiResponse.onSuccess(code, null);
    }

    @PostMapping("/stores/{storeId}/missions")
    public ApiResponse<Void> createMission(
            @PathVariable Long storeId,
            @RequestBody @Valid MissionReqDTO.CreateMission dto
    ){
        BaseSuccessCode code = MissionSuccessCode.MISSION_CREATED;
        return ApiResponse.onSuccess(code, missionService.createMission(storeId, dto));
    }

    /*
    // 오프셋 페이징 - 가게 미션 조회
    @GetMapping("/stores/{storeId}/missions")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.GetMission>> getMissions(
            @PathVariable Long storeId,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort
    ){
        BaseSuccessCode code = MissionSuccessCode.MISSION_VIEW;
        return ApiResponse.onSuccess(code, missionService.getMissionsByStoreId(storeId, pageSize, pageNumber, sort));
    }
     */

    // 커서 페이징 - 가게 미션 조회
    @GetMapping("/stores/{storeId}/missions")
    public ApiResponse<MissionResDTO.Pagination<MissionResDTO.GetMission>> getMissions(
            @PathVariable Long storeId,
            @RequestParam Integer pageSize,
            @RequestParam String cursor,
            @RequestParam String query
    ){
        BaseSuccessCode code = MissionSuccessCode.MISSION_VIEW;
        return ApiResponse.onSuccess(code, missionService.getMissionsByStoreId(storeId, pageSize, cursor, query));
    }

    @PostMapping("/member-mission/in-progress")
    public ApiResponse<MissionResDTO.PaginationOffset<MissionResDTO.MissionPreview>> getMyMissionsInProgress(
            @RequestBody @Valid MissionReqDTO.GetMyMissionInProgress dto
    ){
        BaseSuccessCode code = MissionSuccessCode.MISSION_VIEW;
        return ApiResponse.onSuccess(code,
                        missionService
                                .getMyMissionsInProgressByMemberId(
                                        dto.memberId(),
                                        dto.pageSize(),
                                        dto.pageNumber()));
    }
}
