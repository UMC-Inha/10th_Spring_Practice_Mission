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

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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

    @Transactional(readOnly = true)
    public ReviewResponseDTO.CursorPage<ReviewResponseDTO.ReviewDTO> getMyReviews(
            Long memberId, String query, String cursor, Integer pageSize) {

        if (!memberRepository.existsById(memberId)) {
            throw new MemberException(MemberErrorCode.MEMBER_NOT_FOUND);
        }

        Slice<Review> slice;

        // query = star or id
        // 별점순
        if ("star".equals(query)) {
            BigDecimal starCursor = new BigDecimal("5.1");
            Long idCursor = 0L;

            if (cursor != null) {
                String[] parts = cursor.split(":");
                starCursor = new BigDecimal(parts[0]);  // star = 4.5
                idCursor = Long.parseLong(parts[1]);    // id값. 별점이 같은 리뷰가 여러 개일 수 있으므로
            }

            slice = reviewRepository.findByMemberIdOrderByStar(memberId, idCursor, starCursor, PageRequest.of(0, pageSize));
        } // 아이디 순
        else {
            Long idCursor = Long.MAX_VALUE; // 아이디 최댓값

            if (cursor != null) {
                idCursor = Long.parseLong(cursor);
            }

            slice = reviewRepository.findByMemberIdOrderById(memberId, idCursor, PageRequest.of(0, pageSize));
        }

        List<ReviewResponseDTO.ReviewDTO> items = slice.getContent().stream()
                .map(review -> {
                    ReviewReply reply = reviewReplyRepository.findByReview_Id(review.getId())
                            .stream().findFirst().orElse(null);
                    return ReviewConverter.toReviewDTO(review, reply);
                })
                .collect(Collectors.toList());

        String nextCursor = null;
        if (slice.hasNext()) {
            Review last = slice.getContent().get(slice.getContent().size() - 1);  // 마지막 인덱스
            if ("star".equals(query)) {
                nextCursor = last.getStar().toPlainString() + ":" + last.getId();
            } else {
                nextCursor = String.valueOf(last.getId());
            }
        }

        return ReviewConverter.toCursorPage(slice, items, nextCursor);
    }
}
