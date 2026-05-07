package umc.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;

public class ReviewResponseDTO {

    @Builder
    public record Create(
            Long reviewId,
            LocalDateTime createdAt
    ) {}
}
