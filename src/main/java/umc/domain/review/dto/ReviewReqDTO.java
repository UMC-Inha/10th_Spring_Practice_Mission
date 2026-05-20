package umc.domain.review.dto;

import jakarta.validation.constraints.*;

import java.util.List;

public class ReviewReqDTO {

    public record ReviewCreateDTO(
            @NotBlank String title,
            @NotBlank String reviewContent,
            @NotNull @Min(1) @Max(5) Integer starRating,
            List<String> imageUrls
    ) {}
}
