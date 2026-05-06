package umc.domain.review.dto;

import java.util.List;

public class ReviewReqDTO {

    public record ReviewCreateDTO(
            String reviewContent,
            Integer starRating,
            List<String> imageUrls
    ) {}
}
