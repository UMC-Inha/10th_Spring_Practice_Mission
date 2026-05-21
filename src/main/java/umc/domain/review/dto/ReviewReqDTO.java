package umc.domain.review.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class ReviewReqDTO {

    public record CreateReview(
            @NotBlank String content,
            @NotNull Integer rating,
            List<String> images
    ) {}
}
