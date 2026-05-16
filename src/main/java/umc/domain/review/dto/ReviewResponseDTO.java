package umc.domain.review.dto;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

public class ReviewResponseDTO {

    // 리뷰 작성
    @Builder
    public record Create(
            Long reviewId,
            LocalDateTime createdAt
    ) {}

    // 답글 작성
    @Builder
    public record CreateReply(
            Long replyId,
            LocalDateTime createdAt
    ) {}

    // 리뷰 답글 조회 dto
    @Builder
    public record ReviewReplyDTO(
            String content,
            LocalDateTime date
    ) {}

    // 리뷰 조회 dto
    @Builder
    public record ReviewDTO(
            Long reviewId,
            String nickname,
            LocalDateTime date,
            BigDecimal star,
            String content,
            ReviewReplyDTO reply
    ) {}

    @Builder
    public record CursorPage<T>(
            List<T> data,
            String nextCursor,
            Boolean hasNext,
            Integer pageSize
    ) {}
}
