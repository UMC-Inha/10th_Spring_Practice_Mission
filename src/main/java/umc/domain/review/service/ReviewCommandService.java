package umc.domain.review.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import lombok.RequiredArgsConstructor;
import umc.domain.member.entity.Member;
import umc.domain.member.exception.MemberException;
import umc.domain.member.exception.code.MemberErrorCode;
import umc.domain.member.repository.MemberRepository;
import umc.domain.review.converter.ReviewConverter;
import umc.domain.review.dto.ReviewReqDTO;
import umc.domain.review.entity.Review;
import umc.domain.review.repository.ReviewRepository;
import umc.domain.store.entity.Store;
import umc.domain.store.exception.StoreException;
import umc.domain.store.exception.code.StoreErrorCode;
import umc.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class ReviewCommandService {

	private final ReviewRepository reviewRepository;
	private final MemberRepository memberRepository;
	private final StoreRepository storeRepository;

	@Transactional
	public Review createReview(Long memberId, Long storeId, ReviewReqDTO.ReviewCreateDTO request) {
		Member member = memberRepository.findById(memberId)
			.orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

		Store store = storeRepository.findById(storeId)
			.orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

		Review newReview = ReviewConverter.toReviewEntity(request, member, store);

		return reviewRepository.save(newReview);
	}
}
