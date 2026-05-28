package umc.domain.store.converter;

import umc.domain.member.entity.Member;
import umc.domain.store.dto.StoreReqDTO;
import umc.domain.store.dto.StoreResDTO;
import umc.domain.store.entity.Review;
import umc.domain.store.entity.Store;

public class StoreConverter {

    // 가게 생성 (ReqDTO → Entity)
    public static Store toPutStore(StoreReqDTO.CreateStoreDTO dto) {
        return Store.builder()
                .store_nm(dto.store_nm())
                .region_nm(dto.region_nm())
                .open_dt(dto.open_dt())
                .close_dt(dto.close_dt())
                .build();
    }

    // 가게 생성 조회 (Entity → ResDTO)
    public static StoreResDTO.GetCreateStoreDTO toGetStore(Store store) {
        return new StoreResDTO.GetCreateStoreDTO(
                store.getId(),
                store.getCreatedAt()
        );
    }

    // 리뷰 생성 (ReqDTO → Entity)
    public static Review toPutReview(
            StoreReqDTO.CreateReviewDTO dto, Member member, Store store
    ) {
        return Review.builder()
                .review_text(dto.review_text())
                .star_point(dto.star_point())
                .img_id(dto.img_id())
                .member(member)
                .store(store)
                .build();
    }

    // 리뷰 생성 조회 (Entity → ResDTO)
    public static StoreResDTO.GetCreateReviewDTO toGetReview(Review review) {
        return new StoreResDTO.GetCreateReviewDTO(
                review.getId(),
                review.getCreatedAt()
        );
    }

    // 가게 조회
    public static StoreResDTO.GetStoreInfoDTO toGetStoreInfo(Store store) {
        return StoreResDTO.GetStoreInfoDTO.builder()
                .store_nm(store.getStore_nm())
                .region_nm(store.getRegion_nm())
                .open_dt(store.getOpen_dt())
                .close_dt(store.getClose_dt())
                .build();
    }

    // 리뷰 조회
    public static StoreResDTO.GetReviewInfoDTO toGetReviewInfo(Review review) {
        return StoreResDTO.GetReviewInfoDTO.builder()
                .review_text(review.getReview_text())
                .star_point(review.getStar_point())
                .img_id(review.getImg_id())
                .build();
    }
}