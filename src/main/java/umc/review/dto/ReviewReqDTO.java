package umc.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Builder;

public class ReviewReqDTO {

    @Builder
    public record ReviewInfoDTO(
            @NotNull(message = "별점은 필수 입력 입니다.")
            @Min(value = 1, message = "별점은 최소 1점 입니다.")
            @Max(value = 5, message = "별점은 최대 5점 입니다.")
            Integer star,

            @NotBlank(message = "리뷰 내용은 필수 입니다.")
            String content,

            @NotBlank(message = "사진 url은 필수 입니다.")
            String photoUrl
    ) {}
}
