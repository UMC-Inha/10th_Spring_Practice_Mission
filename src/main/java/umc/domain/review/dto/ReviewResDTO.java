package umc.domain.review.dto;

import lombok.Builder;

import java.time.LocalDateTime;
import java.util.List;

public class ReviewResDTO {

    @Builder
    public record ReviewCreateDTO(
            Long reviewId,
            String reviewContent,
            Integer starRating,
            List<Image> images
    ) {
        public record Image(
                String imageUrl
        ) {}
    }

    @Builder
    public record ReviewDTO(
            Long reviewId,
            Long memberId,
            String nickname,
            String content,
            Integer starRating,
            LocalDateTime createdAt,
            List<String> images,
            List<ReviewReplyDTO> replies
    ) {

        @Builder
        public record ReviewReplyDTO(
                Long reviewReplyId,
                String content,
                LocalDateTime createdAt
        ) {}
    }

    @Builder
    public record CursorPage<T>(
            List<T> data,
            String nextCursor,
            boolean hasNext,
            Integer pageSize
    ) {}
}
