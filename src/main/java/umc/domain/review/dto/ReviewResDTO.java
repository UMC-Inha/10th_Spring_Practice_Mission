package umc.domain.review.dto;

import lombok.Builder;

import java.util.List;

public class ReviewResDTO {

    @Builder
    public record CreateReview(
            Long reviewId,
            String content,
            Integer rating,
            List<String> imageUrls
    ){}

    @Builder
    public record GetMyReview(
            Long reviewId,
            String content,
            Integer rating
    ) {}

    @Builder
    public record PaginationCursor<T>(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ){}

}
