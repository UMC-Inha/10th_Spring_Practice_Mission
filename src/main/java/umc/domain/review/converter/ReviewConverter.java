package umc.domain.review.converter;

import umc.domain.member.entity.Member;
import umc.domain.review.dto.ReviewRequestDTO;
import umc.domain.review.dto.ReviewResponseDTO;
import umc.domain.review.entity.Review;
import umc.domain.store.entity.Store;

public class ReviewConverter {

    // 클라이언트 요청 -> DB에 저장할 엔티티로 변환
    public static Review toReview(ReviewRequestDTO.Create dto, Member member, Store store) {
        return Review.builder()
                .member(member)
                .store(store)
                .star(dto.star())
                .content(dto.content())
                .build();
    }

    // DB에 저장된 엔티티 -> 클라이언트 응답 DTO 변환
    public static ReviewResponseDTO.Create toCreateResponseDTO(Review review) {
        return ReviewResponseDTO.Create.builder()
                .reviewId(review.getId())
                .createdAt(review.getCreatedAt())
                .build();
    }
}