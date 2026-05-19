package umc.domain.review.dto;

import lombok.Builder;
import umc.domain.review.entity.ReviewPhoto;
import umc.domain.review.entity.ReviewReply;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Builder
    public record CreateReviewRes(
            Long reviewId,
            LocalDateTime createdAt
    ){}

    @Builder
    public record ReviewRes(
            Long reviewId,
            double rate,
            String content,
            List<ReviewPhoto> imageUrls,
            LocalDateTime createdAt,
            String name,
            List<ReviewReply> reviewReplyList
    ){}
}
