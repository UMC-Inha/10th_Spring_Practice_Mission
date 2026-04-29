package umc.review.dto;

import lombok.Builder;

public class ReviewReqDTO {

    @Builder
    public record ReviewInfoDTO(
            Integer star,
            String content,
            String photoUrl
    ) {}
}
