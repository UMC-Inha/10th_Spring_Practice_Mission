package umc.domain.store.converter;

import umc.domain.member.entity.Member;
import umc.domain.store.dto.StoreResDTO;
import umc.domain.store.entity.Review;
import umc.domain.store.entity.Store;

public class StoreConverter {
    public static StoreResDTO.GetStoreInfo toGetStoreInfo(
            Store store
    ) {
        return StoreResDTO.GetStoreInfo.builder()
                .store_nm(store.getStore_nm())
                .region_nm(store.getRegion_nm())
                .open_dt(store.getOpen_dt())
                .close_dt(store.getClose_dt())
                .build();
    }

    public static StoreResDTO.GetReviewInfo toGetReviewInfo(
            Review review
    ) {
        return StoreResDTO.GetReviewInfo.builder()
                .review_text(review.getReview_text())
                .star_point(review.getStar_point())
                .img_id(review.getImg_id())
                .build();
    }
}
