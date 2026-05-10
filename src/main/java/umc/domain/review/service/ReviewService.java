package umc.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.member.entity.Member;
import umc.domain.member.exception.MemberException;
import umc.domain.member.exception.code.MemberErrorCode;
import umc.domain.member.repository.MemberRepository;
import umc.domain.review.converter.ReviewConverter;
import umc.domain.review.dto.ReviewRequestDTO;
import umc.domain.review.dto.ReviewResponseDTO;
import umc.domain.review.entity.Review;
import umc.domain.review.entity.ReviewReply;
import umc.domain.review.exception.ReviewException;
import umc.domain.review.exception.code.ReviewErrorCode;
import umc.domain.review.repository.ReviewRepository;
import umc.domain.review.repository.ReviewReplyRepository;
import umc.domain.store.entity.Store;
import umc.domain.store.exception.StoreException;
import umc.domain.store.exception.code.StoreErrorCode;
import umc.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final ReviewReplyRepository reviewReplyRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public ReviewResponseDTO.Create createReview(Long storeId, Long memberId, ReviewRequestDTO.Create dto) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException((MemberErrorCode.MEMBER_NOT_FOUND)));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException((StoreErrorCode.STORE_NOT_FOUND)));

        Review review = ReviewConverter.toReview(dto, member, store);
        Review saved = reviewRepository.save(review);

        return ReviewConverter.toCreateResponseDTO(saved);
    }

    @Transactional
    public ReviewResponseDTO.CreateReply createReply(Long reviewId, ReviewRequestDTO.CreateReply dto) {

        Review review = reviewRepository.findById(reviewId)
                .orElseThrow(() -> new ReviewException(ReviewErrorCode.REVIEW_NOT_FOUND));

        ReviewReply reviewReply = ReviewConverter.toReviewReply(dto, review);
        ReviewReply saved = reviewReplyRepository.save(reviewReply);

        return ReviewConverter.toCreateReplyResponseDTO(saved);
    }
}
