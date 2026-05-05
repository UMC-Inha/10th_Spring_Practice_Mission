package umc.domain.review.dto;

public class ReviewReqDTO {
    public record ReviewDto(
            Double score,
            String text
    ){}
}
