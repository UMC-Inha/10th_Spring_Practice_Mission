package umc.domain.mission.controller;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import umc.domain.mission.dto.MissionReqDTO;
import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.enums.MissionSortType;
import umc.domain.mission.enums.MissionStatus;
import umc.domain.mission.exception.code.MissionSuccessCode;
import umc.global.apiPayload.ApiResponse;

import umc.domain.mission.service.MissionService;

import java.time.LocalDate;
import java.util.List;

@RestController
@RequestMapping("/api/missions")
@RequiredArgsConstructor
@Validated
public class MissionController {

    private final MissionService missionService;

    @GetMapping
    public ApiResponse<MissionResDTO.CursorPage> getMissions(
            @RequestParam @NotEmpty List<MissionStatus> missionStatus,
            @RequestParam(required = false) LocalDate cursorDueDate,
            @RequestParam(required = false) Long cursorId,
            @RequestParam(defaultValue = "10") @Min(1) int pageSize
    ){
        return ApiResponse.onSuccess(MissionSuccessCode.MISSIONS_VIEW,
                missionService.getMissions(1L, missionStatus, cursorDueDate, cursorId, pageSize));
    }

    /**
     * 내가 진행 중인 미션 조회하기 (오프셋 기반 페이징)
     *
     * 진행 중인 미션 최신 순, 마감 기한 임박 순 으로 정렬 가능
     */
    @GetMapping("/offset")
    public ApiResponse<MissionResDTO.OffsetPage> getMissionsUsingOffset(
            @RequestBody @Valid MissionReqDTO.MissionViewDTO reqDto,
            @RequestParam @NotEmpty List<MissionStatus> statuses,
            @RequestParam @Min(0) int page,
            @RequestParam MissionSortType sort,
            @RequestParam(defaultValue = "10") int pageSize
    ){
        return ApiResponse.onSuccess(MissionSuccessCode.MISSIONS_VIEW,
                missionService.getMissionsUsingOffset(reqDto, statuses, page, sort, pageSize));
    }

    @PatchMapping("/{memberMissionId}")
    public ApiResponse<MissionResDTO.MissionStatusUpdateDTO> updateMissionStatus(
            @PathVariable @Positive @NotNull Long memberMissionId,
            @RequestBody @Valid MissionReqDTO.MissionStatusUpdateDTO reqDto
    ) {
        return ApiResponse.onSuccess(MissionSuccessCode.MISSION_STATUS_UPDATED, null);
    }
}
