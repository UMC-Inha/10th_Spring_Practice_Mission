package umc.review.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import umc.review.dto.ReviewReqDTO;
import umc.review.dto.ReviewResDTO;
import umc.review.entity.Review;
import umc.review.repository.ReviewRepository;
import umc.store.entity.Store;
import umc.store.exception.StoreException;
import umc.store.exception.code.StoreErrorCode;
import umc.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;

    public ReviewResDTO.CreateReviewDTO createReview(Long storeId, ReviewReqDTO.ReviewInfoDTO dto) {

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Review newReview = Review.builder()
                .star(dto.star())
                .content(dto.content())
                .photoUrl(dto.photoUrl())
                .store(store) // 조회한 가게 엔티티를 넣어 연관관계를 설정합니다.
                .build();
    }
}
