package umc.domain.store.dto;

import lombok.Builder;

public class StoreResDTO {
    @Builder
    public record GetStoreInfo(
            String store_nm,
            String region_nm,
            String open_dt,
            String close_dt
    ){}

    @Builder
    public record GetReviewInfo(
            String review_text,
            String star_point,
            String img_id,
            String member_id,
            String store_id
    ){}


}
