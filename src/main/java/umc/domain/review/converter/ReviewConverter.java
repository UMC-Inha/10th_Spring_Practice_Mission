package umc.domain.review.converter;

import umc.domain.member.entity.Member;
import umc.domain.review.dto.ReviewReqDTO;
import umc.domain.review.dto.ReviewResDTO;
import umc.domain.review.entity.Review;
import umc.domain.store.entity.Store;

public class ReviewConverter {


    public static Review toReviewEntity(ReviewReqDTO.ReviewReq req, Member member, Store store) {
        return Review.builder()
                .member(member)
                .store(store)
                .rate(req.rate())
                .content(req.content())
                .build();
    }

    public static ReviewResDTO.ReviewRes toReviewRes(Review saved) {
        return ReviewResDTO.ReviewRes.builder()
                .reviewId(saved.getId())
                .rate(saved.getRate())
                .content(saved.getContent())
                .build();
    }

}
