package umc.domain.review.converter;

import umc.domain.member.entity.Member;
import umc.domain.review.dto.ReviewReqDTO;
import umc.domain.review.dto.ReviewResDTO;
import umc.domain.review.entity.Review;
import umc.domain.store.entity.Store;

public class ReviewConverter {

	// 클라이언트 요청 저장
	public static Review toReviewEntity(ReviewReqDTO.ReviewCreateDTO request, Member member, Store store) {
		return Review.builder()
			.member(member)
			.store(store)
			.star(request.getStar())
			.content(request.getReviewContent())
			.build();
	}

	// DB 내용 클라이언트에게 돌려줄 응답
	public static ReviewResDTO.CreateReviewResultDTO toReviewResultDTO(Review review) {
		return ReviewResDTO.CreateReviewResultDTO.builder()
			.reviewId(review.getId())
			.createdAt(review.getCreatedAt())
			.build();
	}
}


