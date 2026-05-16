package umc.domain.review.service;

import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import umc.domain.member.entity.Member;
import umc.domain.member.exception.MemberException;
import umc.domain.member.exception.code.MemberErrorCode;
import umc.domain.member.repository.MemberRepository;
import umc.domain.mission.exception.MissionException;
import umc.domain.mission.exception.code.MissionErrorCode;
import umc.domain.review.converter.ReviewConverter;
import umc.domain.review.dto.ReviewReqDTO;
import umc.domain.review.dto.ReviewResDTO;
import umc.domain.review.entity.Review;
import umc.domain.review.exception.ReviewException;
import umc.domain.review.exception.code.ReviewErrorCode;
import umc.domain.review.repository.ReviewRepository;
import umc.domain.store.entity.Store;
import umc.domain.store.exception.StoreException;
import umc.domain.store.exception.code.StoreErrorCode;
import umc.domain.store.repository.StoreRepository;
import umc.global.dto.CusorResDTO;

@Service
@AllArgsConstructor
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final MemberRepository memberRepository;
    private final StoreRepository storeRepository;

    public ReviewResDTO.CreateReviewRes createReview(Long memberId, Long storeId, ReviewReqDTO.CreateReviewReq req) {
        Member member = memberRepository.findById(memberId).orElseThrow(
                ()->new MemberException(MemberErrorCode.MEMBER_NOT_FOUND)
        );

        Store store = storeRepository.findById(storeId).orElseThrow(
                ()->new StoreException(StoreErrorCode.STORE_NOT_FOUND)
        );

        Review newReview = ReviewConverter.toReviewEntity(req, member, store);


        Review saved = reviewRepository.save(newReview);

        return ReviewConverter.toCreateReviewRes(saved);
    }

    public CusorResDTO.Pagination<ReviewResDTO.ReviewRes> getMyReviews(ReviewReqDTO.MyReview req) {

        PageRequest pageRequest = PageRequest.of(0, req.pageSize());

        Slice<Review> reviewList;
        String nextCursor=null;
        Long idCursor;

        Member member = memberRepository.findById(req.id())
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));


        //cursor = id : rating
        if (!req.cursor().equals("-1")) {

            String[] cursorSplit = req.cursor().split(":");

            switch (req.sort().toLowerCase()) {
                case "id":
                    idCursor = Long.parseLong(cursorSplit[0]);

                    reviewList = reviewRepository.findReviewsByMemberIdInOrderByReviewId(
                            member.getId(),
                            idCursor,
                            pageRequest
                    );

                    if (reviewList.hasNext()){
                        nextCursor = reviewList.getContent().get(reviewList.getContent().size()-1).getId()+":0";
                    }

                    break;

                case "rate":
                    Double ratingCursor = Double.parseDouble(cursorSplit[1]);
                    idCursor = Long.parseLong(cursorSplit[0]);

                    reviewList = reviewRepository.findAllByMemberIdOrderByRatingDesc(
                            member.getId(),
                            ratingCursor,
                            idCursor,
                            pageRequest
                    );

                    if (reviewList.hasNext()){
                        nextCursor =reviewList.getContent().get(reviewList.getContent().size()-1).getId() +":"+ reviewList.getContent().get(reviewList.getContent().size()-1).getRate();
                    }

                    break;
                default:
                    throw new ReviewException(ReviewErrorCode.INVALID_SORT_TYPE);
            }
        }
        else{
            reviewList = reviewRepository.findAllByMember(member, pageRequest);
        }

        return ReviewConverter.toPagination(
                reviewList
                , nextCursor
        );
    }
}
