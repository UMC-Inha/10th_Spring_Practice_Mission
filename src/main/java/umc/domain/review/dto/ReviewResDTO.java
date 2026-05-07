package umc.domain.review.dto;

import lombok.Builder;

import java.util.List;

public class ReviewResDTO {

    @Builder
    public record CreateReview(
            Long reviewId,
            String content,
            Integer rating,
            List<String> imageUrls
    ){}
}
