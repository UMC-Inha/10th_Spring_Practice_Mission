package umc.review.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import umc.apiPayload.converter.BaseConverter;
import umc.apiPayload.dto.BaseResDTO;
import umc.member.entity.Member;
import umc.member.exception.MemberException;
import umc.member.exception.code.MemberErrorCode;
import umc.member.repository.MemberRepository;
import umc.mission.converter.MissionConverter;
import umc.mission.dto.MissionResDTO;
import umc.review.converter.ReviewConverter;
import umc.review.dto.ReviewReqDTO;
import umc.review.dto.ReviewResDTO;
import umc.review.entity.Review;
import umc.review.exception.ReviewException;
import umc.review.exception.code.ReviewErrorCode;
import umc.review.repository.ReviewRepository;
import umc.store.entity.Store;
import umc.store.exception.StoreException;
import umc.store.exception.code.StoreErrorCode;
import umc.store.repository.StoreRepository;

@Service
@RequiredArgsConstructor
@Transactional
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final StoreRepository storeRepository;
    private final MemberRepository memberRepository;

    public ReviewResDTO.CreateReviewDTO createReview(Long storeId, Long memberId, ReviewReqDTO.ReviewInfoDTO dto) {

        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        Store store = storeRepository.findById(storeId)
                .orElseThrow(() -> new StoreException(StoreErrorCode.STORE_NOT_FOUND));

        Review newReview = ReviewConverter.toReview(dto, store, member);

        Review savedReview = reviewRepository.save(newReview);

        return ReviewConverter.toCreateReviewDTO(savedReview);
    }

    public BaseResDTO.SliceResponse<ReviewResDTO.ReviewDetailDTO> getMyReviews(
            Long memberId,
            Integer pageSize,
            String cursor,
            String query
    ){
        PageRequest pageRequest = PageRequest.of(0, pageSize);

        Slice<Review> reviewList;
        String nextCursor;

        if(!cursor.equals("-1")){
            String[] cursorSplit = cursor.split(":");
            switch(query.toLowerCase()){
                case "id":
                    long idCursor;
                    idCursor = Long.parseLong(cursorSplit[1]);

                    reviewList = reviewRepository.findAllByMemberIdAndIdLessThanOrderByIdDesc(
                            memberId,
                            idCursor,
                            pageRequest
                    );
                    break;
                case "star":
                    int starCursor = Integer.parseInt(cursorSplit[0]);
                    reviewList = reviewRepository.findAllByMemberIdAndStarLessThanOrderByStarDescIdDesc(
                            memberId,
                            starCursor,
                            pageRequest
                    );
                    break;

                default:
                    //아무 에러
                    throw new ReviewException(ReviewErrorCode.REVIEW_NOT_FOUND);
            }

        }
        else{
            if ("star".equalsIgnoreCase(query)) {
                reviewList = reviewRepository.findAllByMemberIdOrderByStarDescIdDesc(memberId, pageRequest);
            } else {
                reviewList = reviewRepository.findAllByMemberIdOrderByIdDesc(memberId, pageRequest);
            }
        }
        nextCursor = reviewList.getContent().getLast().getStar() + ":" + reviewList.getContent().getLast().getId();

        return BaseConverter.toSliceResponse(
                reviewList.map(ReviewConverter::reviewToReviewDetailDTO).toList(),
                reviewList.hasNext(),
                nextCursor,
                pageSize
        );
    }
}
