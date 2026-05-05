package umc.review.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import umc.review.converter.ReviewConverter;
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

        Review newReview = ReviewConverter.toReview(dto, store);

        Review savedReview = reviewRepository.save(newReview);

        return ReviewConverter.toCreateReviewDTO(savedReview);
    }
}
