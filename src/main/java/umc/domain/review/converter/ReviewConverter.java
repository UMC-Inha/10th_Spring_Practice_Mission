package umc.domain.review.converter;

import org.springframework.data.domain.Slice;
import umc.domain.member.entity.Member;
import umc.domain.review.dto.ReviewReqDTO;
import umc.domain.review.dto.ReviewResDTO;
import umc.domain.review.entity.Review;
import umc.domain.store.entity.Store;
import umc.global.dto.CusorResDTO;

import static java.util.stream.Collectors.toList;

public class ReviewConverter {


    public static Review toReviewEntity(ReviewReqDTO.CreateReviewReq req, Member member, Store store) {
        return Review.builder()
                .member(member)
                .store(store)
                .rate(req.rate())
                .content(req.content())
                .build();
    }

    public static ReviewResDTO.CreateReviewRes toCreateReviewRes(Review saved) {
        return ReviewResDTO.CreateReviewRes.builder()
                .reviewId(saved.getId())
                .createdAt(saved.getCreatedAt())
                .build();
    }

    // toPagination용
    public static ReviewResDTO.ReviewRes toReviewRes(Review review) {
        return ReviewResDTO.ReviewRes.builder()
                .reviewId(review.getId())
                .rate(review.getRate())
                .content(review.getContent())
                .imageUrls(review.getReviewPhotoList())
                .createdAt(review.getCreatedAt())
                .name(review.getMember().getName())
                .reviewReplyList(review.getReviewReplyList())
                .build();
    }

    public static CusorResDTO.Pagination<ReviewResDTO.ReviewRes> toPagination(Slice<Review> reviewList, String nextCursor) {
        return CusorResDTO.Pagination.<ReviewResDTO.ReviewRes>builder()
                .data(reviewList.stream()
                        .map(ReviewConverter::toReviewRes)
                        .toList())
                .nextCursor(nextCursor)
                .hasNext(reviewList.hasNext())
                .pageSize(reviewList.getSize())
                .build();
    }
}
