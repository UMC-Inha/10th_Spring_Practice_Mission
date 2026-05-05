package umc.domain.review.service;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import umc.domain.member.entity.Member;
import umc.domain.member.exception.MemberException;
import umc.domain.member.exception.code.MemberErrorCode;
import umc.domain.member.repository.MemberRepository;
import umc.domain.review.converter.ReviewConverter;
import umc.domain.review.dto.ReviewReqDTO;
import umc.domain.review.dto.ReviewResDTO;
import umc.domain.review.entity.Review;
import umc.domain.review.repository.ReviewRepository;
import umc.domain.store.entity.Store;
import umc.domain.store.exception.StoreException;
import umc.domain.store.exception.code.StoreErrorCode;
import umc.domain.store.repository.StoreRepository;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    public ReviewResDTO.ReviewRes createReview(Long memberId, Long storeId, ReviewReqDTO.ReviewReq req) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                ()->new MemberException(MemberErrorCode.MEMBER_NOT_FOUND)
        );

        Store store = storeRepository.findById(storeId).orElseThrow(
                ()->new StoreException(StoreErrorCode.STORE_NOT_FOUND)
        );

        Review newReview = ReviewConverter.toReviewEntity(req, member, store);


        Review saved = reviewRepository.save(newReview);

        return ReviewConverter.toReviewRes(saved);
    }
}
