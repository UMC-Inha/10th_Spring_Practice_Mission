package umc.domain.store.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class StoreReqDTO {

    // 가게 생성
    public record CreateStoreDTO(
            @NotBlank
            String store_nm,
            @NotBlank
            String region_nm,
            @NotBlank
            String open_dt,
            @NotBlank
            String close_dt
    ){}

    // 리뷰 생성
    public record CreateReviewDTO(
            @NotBlank
            String review_text,
            @NotBlank
            String star_point,
            @NotBlank
            String img_id
    ){}

}