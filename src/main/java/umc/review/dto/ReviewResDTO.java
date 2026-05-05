package umc.review.dto;

import lombok.Builder;

public class ReviewResDTO {
    @Builder
    public record CreateReviewDTO(
            Long reviewId
    ) {}
}
