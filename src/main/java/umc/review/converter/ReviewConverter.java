package umc.review.converter;

import umc.member.entity.Member;
import umc.review.dto.ReviewReqDTO;
import umc.review.dto.ReviewResDTO;
import umc.review.entity.Review;
import umc.store.entity.Store;

public class ReviewConverter {

    public static Review toReview(ReviewReqDTO.ReviewInfoDTO request, Store store, Member member) {
        return Review.builder()
                .member(member)
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

    public static ReviewResDTO.ReviewDetailDTO reviewToReviewDetailDTO(Review review){
        return ReviewResDTO.ReviewDetailDTO.builder()
                .reviewId(review.getId())
                .storeName(review.getStore().getName())
                .star(review.getStar())
                .content(review.getContent())
                .createdAt(review.getCreatedAt())
                .build();
    }
}
