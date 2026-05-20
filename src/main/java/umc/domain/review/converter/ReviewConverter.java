package umc.domain.review.converter;

import org.springframework.data.domain.Slice;
import umc.domain.member.entity.Member;
import umc.domain.review.dto.ReviewReqDTO;
import umc.domain.review.dto.ReviewResDTO;
import umc.domain.review.entity.Review;
import umc.domain.review.entity.ReviewReply;
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

    public static ReviewResDTO.ReviewDTO.ReviewReplyDTO toReviewReplyDTO(ReviewReply reviewReply) {
        return ReviewResDTO.ReviewDTO.ReviewReplyDTO.builder()
                .reviewReplyId(reviewReply.getId())
                .content(reviewReply.getContent())
                .createdAt(reviewReply.getCreatedAt())
                .build();
    }

    public static ReviewResDTO.ReviewDTO toReviewDTO(Review review) {
        return ReviewResDTO.ReviewDTO.builder()
                .reviewId(review.getId())
                .memberId(review.getMember().getId())
                .nickname(review.getMember().getNickname())
                .content(review.getContent())
                .starRating(review.getStarRating())
                .createdAt(review.getCreatedAt())
                .images(review.getReviewPhotoList().stream()
                        .map(photo -> photo.getPhotoUrl())
                        .toList())
                .replies(review.getReviewReplyList().stream()
                        .map(ReviewConverter::toReviewReplyDTO)
                        .toList())
                .build();
    }

    public static ReviewResDTO.CursorPage toCursorPage(Slice<Review> reviews, String nextCursor) {
        return ReviewResDTO.CursorPage.<ReviewResDTO.ReviewDTO>builder()
                .data(reviews.getContent().stream()
                        .map(ReviewConverter::toReviewDTO)
                        .toList()
                )
                .nextCursor(nextCursor)
                .hasNext(reviews.hasNext())
                .pageSize(reviews.getSize())
                .build();
    }
}
