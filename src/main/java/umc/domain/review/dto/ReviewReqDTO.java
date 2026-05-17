package umc.domain.review.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class ReviewReqDTO {

    public record CreateReviewReq(
            @NotNull
            @Min(1) @Max(5)
            double rate,
            @NotBlank
            String content,
            List<String> imageUrls
    ){}

    public record MyReview(
            @NotNull
            Long id,
            String cursor,
            Integer pageSize,
            String sort
    ){}

}
