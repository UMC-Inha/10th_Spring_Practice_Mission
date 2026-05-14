package umc.domain.review.dto;

import java.util.List;

public class ReviewReqDTO {

    public record CreateReview(
            String content,
            Integer rating,
            List<String> images
    ) {}
}
