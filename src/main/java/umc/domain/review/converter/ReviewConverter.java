package umc.domain.review.converter;

import umc.domain.member.entity.Member;
import umc.domain.review.dto.ReviewReqDTO;
import umc.domain.review.dto.ReviewResDTO;
import umc.domain.review.entity.Review;
import umc.domain.store.entity.Store;

import java.util.List;

public class ReviewConverter {

    public static Review toReview(Member member, Store store, ReviewReqDTO.ReviewCreateDTO reqDto) {
        return Review.builder()
                .member(member)
                .store(store)
                .content(reqDto.reviewContent())
                .starRating(reqDto.starRating())
                .build();
    }

    public static ReviewResDTO.ReviewCreateDTO toReviewCreateDTO(Review review) {
        List<ReviewResDTO.ReviewCreateDTO.Image> images = review.getReviewPhotoList().stream()
                .map(photo -> new ReviewResDTO.ReviewCreateDTO.Image(photo.getPhotoUrl()))
                .toList();

        return ReviewResDTO.ReviewCreateDTO.builder()
                .reviewId(review.getId())
                .reviewContent(review.getContent())
                .starRating(review.getStarRating())
                .images(images)
                .build();
    }
}
