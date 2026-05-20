package umc.domain.review.service;

import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.member.entity.Member;
import umc.domain.member.exception.MemberException;
import umc.domain.member.exception.code.MemberErrorCode;
import umc.domain.member.repository.MemberRepository;
import umc.domain.review.dto.ReviewReqDTO;
import umc.domain.review.dto.ReviewResDTO;
import umc.domain.review.converter.ReviewConverter;
import umc.domain.review.entity.Review;
import umc.domain.review.exception.ReviewException;
import umc.domain.review.exception.code.ReviewErrorCode;
import umc.domain.review.repository.ReviewRepository;
import umc.domain.store.entity.Store;
import umc.domain.store.exception.StoreException;
import umc.domain.store.exception.code.StoreErrorCode;
import umc.domain.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    @Transactional
    public ReviewResDTO.ReviewCreateDTO createReview(Long memberId, Long storeId, ReviewReqDTO.ReviewCreateDTO reqDto) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.NOT_FOUND));

        Review review = ReviewConverter.toReview(member, store, reqDto);

        if (reqDto.imageUrls() != null) {
            reqDto.imageUrls().forEach(review::addPhoto);
        }

        reviewRepository.save(review);
        return ReviewConverter.toReviewCreateDTO(review);
    }

    public ReviewResDTO.CursorPage getMyReviews(
            Long memberId,
            String query,
            String cursor,
            Integer pageSize
    ) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.NOT_FOUND));

        PageRequest pageRequest = PageRequest.of(0, pageSize);
        Slice<Review> reviews;
        String nextCursor = null;

        // cursor -> "id:rating", cursor가 존재하지 않으면 첫 페이지 제공
        switch (query.toLowerCase()) {
            case "id" -> {
                Long idCursor = cursor != null ? Long.parseLong(cursor.split(":")[0]) : -1L;

                reviews = reviewRepository.findReviewsByMemberIdInOrderByReviewId(
                        memberId, idCursor, pageRequest
                );

                if (reviews.hasNext()) {
                    Review last = reviews.getContent().get(reviews.getContent().size() - 1);
                    nextCursor = last.getId() + ":0";
                }
            }
            case "rating" -> {
                Long idCursor = cursor != null ? Long.parseLong(cursor.split(":")[0]) : -1L;
                Integer ratingCursor = cursor != null ? Integer.parseInt(cursor.split(":")[1]) : Integer.MAX_VALUE;

                reviews = reviewRepository.findReviewsByMemberIdOrderByRating(
                        memberId,
                        idCursor,
                        ratingCursor,
                        pageRequest
                );

                if (reviews.hasNext()) {
                    Review last = reviews.getContent().get(reviews.getContent().size() - 1);
                    nextCursor = last.getId() + ":" + last.getStarRating();
                }
            }
            default -> {
                throw new ReviewException(ReviewErrorCode.NOT_APPLY_CURSOR);
            }
        }

        return ReviewConverter.toCursorPage(reviews, nextCursor);
    }
}
