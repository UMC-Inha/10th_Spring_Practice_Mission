package umc.domain.review.converter;

import org.springframework.data.domain.Slice;
import umc.domain.member.entity.Member;
import umc.domain.review.dto.ReviewRequestDTO;
import umc.domain.review.dto.ReviewResponseDTO;
import umc.domain.review.entity.Review;
import umc.domain.review.entity.ReviewReply;
import umc.domain.store.entity.Store;

import java.util.List;

public class ReviewConverter {

    // 리뷰 작성 요청 -> review 객체 변환
    public static Review toReview(ReviewRequestDTO.Create dto, Member member, Store store) {
        return Review.builder()
                .member(member)
                .store(store)
                .star(dto.star())
                .content(dto.content())
                .build();
    }

    // db에 저장된 review 엔티티 -> dto로 변환
    public static ReviewResponseDTO.Create toCreateResponseDTO(Review review) {
        return ReviewResponseDTO.Create.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }

    // 특정 리뷰에 대한 답글 작성 -> reply 객체 변환
    public static ReviewReply toReviewReply(ReviewRequestDTO.CreateReply dto, Review review) {
        return ReviewReply.builder()
                .review(review)
                .content(dto.content())
                .build();
    }

    // db에 저장된 reply 엔티티 -> dto로 변환
    public static ReviewResponseDTO.CreateReply toCreateReplyResponseDTO(ReviewReply reviewReply) {
        return ReviewResponseDTO.CreateReply.builder()
                .replyId(reviewReply.getId())
                .createdAt(reviewReply.getCreatedAt())
                .build();
    }

    // 리뷰 답글 조회
    public static ReviewResponseDTO.ReviewReplyDTO toReviewReplyDTO(ReviewReply reply) {
        return ReviewResponseDTO.ReviewReplyDTO.builder()
                .content(reply.getContent())
                .date(reply.getCreatedAt())
                .build();
    }

    // 리뷰(답글 포함) 조회
    public static ReviewResponseDTO.ReviewDTO toReviewDTO(Review review, ReviewReply reply) {
        return ReviewResponseDTO.ReviewDTO.builder()
                .reviewId(review.getId())
                .nickname(review.getMember().getName())
                .date(review.getCreatedAt())
                .star(review.getStar())
                .content(review.getContent())
                .reply(reply != null ? toReviewReplyDTO(reply) : null)  // 특정 리뷰에 답글이 달린 경우를 구분
                .build();
    }

    // 커서 페이징 응답
    public static ReviewResponseDTO.CursorPage<ReviewResponseDTO.ReviewDTO> toCursorPage(
            Slice<Review> slice, List<ReviewResponseDTO.ReviewDTO> items, String nextCursor) {
        return ReviewResponseDTO.CursorPage.<ReviewResponseDTO.ReviewDTO>builder()
                .data(items)
                .nextCursor(nextCursor)
                .hasNext(slice.hasNext())
                .pageSize(slice.getSize())
                .build();
    }
}