package umc.mission.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.BaseSuccessCode;
import umc.global.dto.BaseResDTO;
import umc.mission.dto.MissionReqDTO;
import umc.mission.dto.MissionResDTO;
import umc.mission.exception.code.MissionSuccessCode;
import umc.mission.service.MemberMissionService;
import umc.mission.service.MissionService;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class MissionController {
    private final MissionService missionService;
    private final MemberMissionService memberMissionService;

    @GetMapping("/v1/users/me/missions")
    @Operation(summary = "미션 조회 옛날 버전")
    public ApiResponse<MissionResDTO.MissionListDTO> getMyMissions(
            @RequestParam(name = "isComplete") Boolean isComplete,
            @RequestParam(name = "page") Integer page
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.USER_MISSION_OK, missionService.getMyMissions(isComplete, page));
    }

    @PatchMapping("/v1/users/user-missions/{userMissionId}")
    @Operation(summary = "미션 성공 업데이트")
    public ApiResponse<MissionResDTO.MissionCompleteResultDTO> completeMission(
            @PathVariable(name = "userMissionId") Long userMissionId
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.COMPLETE_OK, missionService.completeMissions(userMissionId));
    }

    @PostMapping("/v1/stores/{storeId}/missions")
    @Operation(summary = "가게 미션 생성")
    public ApiResponse<Void> createMission(
            @PathVariable Long storeId,
            @RequestBody MissionReqDTO.CreateMission dto
            ){
        BaseSuccessCode code = MissionSuccessCode.CREATED;
        return ApiResponse.onSuccess(code, missionService.createMission(storeId, dto));
    }

    @GetMapping("/v1/stores/{storeId}/missions")
    @Operation(summary = "가게 미션 조회")
    public ApiResponse<BaseResDTO.Pagination<MissionResDTO.GetMission>> getMissions(
            @PathVariable Long storeId,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, missionService.getMissions(storeId, pageSize, pageNumber, sort));
    }

    @GetMapping("/v1/missions/in-progress")
    @Operation(summary = "진행 중/완료 한 미션 조회")
    public ApiResponse<BaseResDTO.Pagination<MissionResDTO.MissionInfoDTO>> getInProgressMissions(
            @RequestParam Long memberId,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort
    ){
        BaseResDTO.Pagination<MissionResDTO.MissionInfoDTO> result =
                memberMissionService.getInProgressMissions(memberId, pageSize, pageNumber, sort);
        BaseSuccessCode code = MissionSuccessCode.OK;
        return ApiResponse.onSuccess(code, result);
    }
}
