package umc.domain.store.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.domain.member.entity.Member;
import umc.domain.member.exception.MemberException;
import umc.domain.member.exception.code.MemberErrorCode;
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

    // 가게 조회
    public StoreResDTO.GetStoreInfoDTO getStoreInfo(Long storeId) {
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));
        return StoreConverter.toGetStoreInfo(store);
    }

    // 리뷰 조회
    public StoreResDTO.GetReviewInfoDTO getReviewInfo(Long memberId, Long storeId) {
        List<Review> reviews = reviewRepository
                .findByMemberIdAndStoreId(memberId, storeId);
        if (reviews.isEmpty()) {
            throw new StoreException(StoreErrorCode.REVIEW_NOT_FOUND);
        }
        return StoreConverter.toGetReviewInfo(reviews.get(0));
    }

    // 가게 생성
    @Transactional
    public StoreResDTO.GetCreateStoreDTO createStore(StoreReqDTO.CreateStoreDTO dto) {
        Store store = StoreConverter.toPutStore(dto);
        return StoreConverter.toGetStore(storeRepository.save(store));
    }

    // 리뷰 생성
    @Transactional
    public StoreResDTO.GetCreateReviewDTO createReview(
            Long memberId, Long storeId, StoreReqDTO.CreateReviewDTO dto
    ) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Review review = StoreConverter.toPutReview(dto, member, store);
        return StoreConverter.toGetReview(reviewRepository.save(review));
    }
}