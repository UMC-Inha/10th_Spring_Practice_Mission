package umc.domain.review.dto;

import lombok.Builder;

import java.util.List;

public class ReviewResDTO {

    @Builder
    public record ReviewRes(
            Long reviewId,
            double rate,
            String content,
            List<String> imageUrls

    ){}
}
