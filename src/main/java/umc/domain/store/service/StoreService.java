package umc.domain.store.service;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.domain.member.entity.Member;
import umc.domain.store.converter.StoreConverter;
import umc.domain.store.dto.StoreReqDTO;
import umc.domain.store.dto.StoreResDTO;
import umc.domain.store.entity.Review;
import umc.domain.store.entity.Store;
import umc.domain.store.exception.StoreException;
import umc.domain.store.exception.code.StoreErrorCode;
import umc.domain.store.repository.ReviewRepository;
import umc.domain.store.repository.StoreRepository;
import umc.domain.member.repository.MemberRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StoreService {
    private final StoreRepository storeRepository;
    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;

    public StoreResDTO.GetStoreInfo getStoreInfo(StoreReqDTO.GetStoreInfo dto) {
        Long storeId = dto.store_id();
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));
        return StoreConverter.toGetStoreInfo(store);
    }

    public StoreResDTO.GetReviewInfo getReviewInfo(StoreReqDTO.GetReviewInfo dto) {
        List<Review> reviews = reviewRepository
                .findByMemberIdAndStoreId(dto.member_id(), dto.store_id());

        if (reviews.isEmpty()) {
            throw new StoreException(StoreErrorCode.REVIEW_NOT_FOUND);
        }

        return StoreConverter.toGetReviewInfo(reviews.get(0));
    }

    public void createStore() {
        Store store = Store.builder()
                .store_nm("중국집")
                .region_nm("서울시 구로구")
                .open_dt("060001")
                .close_dt("225959")
                .build();

        storeRepository.save(store); // ← 여기서 INSERT 실행
    }

    public void createReview() {
        Member member = memberRepository.findById(2L)
                .orElseThrow(() -> new RuntimeException("멤버 없음"));
        Store store = storeRepository.findById(2L)
                .orElseThrow(() -> new RuntimeException("가게 없음"));

        Review review = Review.builder()
                .review_text("음~ 너무 맛있어요!")
                .star_point("5")
                .img_id("이미지 아이디")
                .member(member)   // ← Member 객체
                .store(store)
                .build();

        reviewRepository.save(review); // ← 여기서 INSERT 실행
    }
}