package umc.domain.store.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class StoreResDTO {

    // 가게 생성 응답
    @Builder
    public record GetCreateStoreDTO(
            Long store_id,
            LocalDateTime createdAt
    ){}

    // 리뷰 생성 응답
    @Builder
    public record GetCreateReviewDTO(
            Long review_id,
            LocalDateTime createdAt
    ){}

    // 가게 조회 응답
    @Builder
    public record GetStoreInfoDTO(
            String store_nm,
            String region_nm,
            String open_dt,
            String close_dt
    ){}

    // 리뷰 조회 응답
    @Builder
    public record GetReviewInfoDTO(
            String review_text,
            String star_point,
            String img_id
    ){}
}
