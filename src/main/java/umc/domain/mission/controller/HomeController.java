package umc.domain.mission.controller;

import io.swagger.v3.oas.annotations.Operation;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import umc.domain.mission.dto.HomeResDTO;
import umc.domain.mission.dto.MissionResDTO;
import umc.domain.mission.exception.code.MissionSuccessCode;
import umc.domain.mission.service.MissionService;
import umc.global.apiPayload.ApiResponse;
import umc.global.apiPayload.code.BaseSuccessCode;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class HomeController {

    private final MissionService missionService;

    @GetMapping("/home")
    @Operation(summary = "홈 화면 종합 정보")
    public ApiResponse<HomeResDTO.Home> getHomeInfo(
            @RequestParam(name = "regionId") Long regionId,
            @RequestParam(name = "cursor", required = false) Long cursor
    ){
        BaseSuccessCode code = MissionSuccessCode.OK;
        //서비스 로직에서 지역 구분, 완료한 미션 계산해서 리턴 받기
        MissionResDTO.MissionPreviewListDTO result = missionService.getHomeInfo(memberId, regionId, cursor);
        return ApiResponse.onSuccess(code, result);
    }

}
