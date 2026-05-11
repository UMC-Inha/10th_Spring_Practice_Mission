package umc.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

public class ReviewReqDTO {

    @Builder
    public record ReviewDTO(
            Long memberId,
            Float score,
            String text
    ){}
}
