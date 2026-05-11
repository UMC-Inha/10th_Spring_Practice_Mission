package umc.review.converter;

import umc.review.dto.ReviewReqDTO;
import umc.review.dto.ReviewResDTO;
import umc.review.entity.Review;
import umc.store.entity.Store;

public class ReviewConverter {

    public static Review toReview(ReviewReqDTO.ReviewInfoDTO request, Store store) {
        return Review.builder()
                .star(request.star())
                .content(request.content())
                .photoUrl(request.photoUrl())
                .store(store)
                .build();
    }

    public static ReviewResDTO.CreateReviewDTO toCreateReviewDTO(Review review) {
        return ReviewResDTO.CreateReviewDTO.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

}
