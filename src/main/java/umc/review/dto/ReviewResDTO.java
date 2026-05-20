package umc.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;

public class ReviewResDTO {
    @Builder
    public record CreateReviewDTO(
            Long reviewId,
            LocalDateTime createdAt
    ) {}

    //ReviewReqDTO에
    @Builder
    public record ReviewDetailDTO(
            Long reviewId,
            String storeName,
            Integer star,
            String content,
            LocalDateTime createdAt
    ) {}
}
