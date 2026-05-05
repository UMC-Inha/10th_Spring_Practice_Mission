package umc.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.Store.entity.Store;
import umc.domain.Store.repository.StoreRepository;
import umc.domain.member.entity.Member;
import umc.domain.member.repository.MemberRepository;
import umc.domain.review.dto.ReviewReqDTO;
import umc.domain.review.dto.ReviewResDTO;
import umc.domain.review.entity.Review;
import umc.domain.review.repository.ReviewRepository;
@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    public ReviewResDTO.CreateReviewResultDTO createReview(Long storeId, ReviewReqDTO.ReviewDTO request){

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new IllegalArgumentException("해당 가게가 없습니다."));
        Member member = memberRepository.findById(request.memberId()) //record라 get함수 안쓰고 필드 이름으로 바로!
                .orElseThrow(() -> new IllegalArgumentException("해당 회원이 없습니다."));

        Review newReview = Review.builder()
                .score(request.score())
                .content(request.text())
                .member(member)
                .store(store)
                .build();

        Review savedReview = reviewRepository.save(newReview);

        return ReviewResDTO.CreateReviewResultDTO.builder()
                .reviewId(savedReview.getId())
                .cretaedAt(savedReview.getCreatedAt())
                .build();
    }
}
