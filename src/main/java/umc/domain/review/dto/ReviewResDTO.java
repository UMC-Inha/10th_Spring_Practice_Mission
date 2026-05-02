package umc.domain.review.dto;

import lombok.Builder;

import java.util.List;

public class ReviewResDTO {

    @Builder
    public record ReviewReq(
            Long reviewId,
            double rate,
            String content,
            List<String> imageUrls

    ){}
}
