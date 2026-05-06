package com.example.umc10th.api.home.controller;

import com.example.umc10th.api.home.dto.HomeResponseDto;
import com.example.umc10th.domain.mission.exception.code.MissionSuccessCode;
import com.example.umc10th.global.apiPayload.ApiResponse;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HomeController {

	@GetMapping("/api/home")
	public ApiResponse<HomeResponseDto> getHome(
		@RequestParam Long regionId,
		@RequestParam(required = false) Long cursor,
		@RequestParam Integer size
	) {
		HomeResponseDto response = new HomeResponseDto(
			999999,
			regionId,
			"오금동",
			7,
			10,
			1000,
			List.of(
				new HomeResponseDto.HomeMissionDto(
					101L,
					1L,
					"춘리마라탕",
					"10,000원 이상의 식사 시",
					500,
					7
				)
			),
			size,
			cursor == null ? 12L : cursor - 1,
			true
		);

		return ApiResponse.onSuccess(MissionSuccessCode.HOME_FOUND, response);
	}
}
