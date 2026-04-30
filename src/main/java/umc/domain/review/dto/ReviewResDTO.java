package umc.domain.review.dto;

import lombok.Builder;

import java.util.List;

public class ReviewResDTO {

    @Builder
    public record ReviewCreateDTO(
            Long reviewId,
            String reviewContent,
            Integer starRating,
            List<Image> images
    ) {
        public record Image(
                String imageUrl
        ) {}
    }
}
