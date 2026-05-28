package umc.domain.mission.controller;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;
import umc.domain.mission.dto.MissionReqDTO;
import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.exception.code.MissionSuccessCode;
import umc.domain.mission.service.MissionService;
import umc.global.apiPayload.ApiResponse;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/stores")
public class MissionController {

    private final MissionService missionService;

    // 가게 미션 생성
    @PostMapping("/{storeId}/missions")
    public ApiResponse<MissionResDTO.GetCreateMissionDTO> createMission(
            @PathVariable Long storeId,
            @RequestBody @Valid MissionReqDTO.CreateMissionDTO dto
    ){
        return ApiResponse.onSuccess(MissionSuccessCode.CREATED,
                missionService.createMission(storeId, dto));
    }

    // 가게 미션 조회
    @GetMapping("/{storeId}/missions")
    public ApiResponse<List<MissionResDTO.GetMissionDTO>> getMissions(
            @PathVariable Long storeId,
            @RequestParam Integer pageSize,
            @RequestParam Integer pageNumber,
            @RequestParam(required = false) String sort
    ){
        return ApiResponse.onSuccess(MissionSuccessCode.OK,
                missionService.getMissions(storeId, pageSize, pageNumber, sort));
    }
}