package umc.domain.review.converter;

import umc.domain.review.dto.ReviewResDTO;
import umc.domain.review.entity.Review;

import java.util.List;

public class ReviewConverter {

    public static ReviewResDTO.CreateReview toCreateReview(Review review) {
        return ReviewResDTO.CreateReview.builder()
                .reviewId(review.getId())
                .content(review.getContent())
                .rating(review.getRating())
                .imageUrls(List.of())
                .build();
    }
}
