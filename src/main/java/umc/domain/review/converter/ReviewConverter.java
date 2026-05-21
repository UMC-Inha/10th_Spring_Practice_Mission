package umc.domain.review.converter;

import umc.domain.review.dto.ReviewReqDTO;
import umc.domain.review.dto.ReviewResDTO;
import umc.domain.review.entity.Review;

import java.util.List;

public class ReviewConverter {

    public static ReviewResDTO.CreateReview toCreateReview(Review review) {
        return ReviewResDTO.CreateReview.builder()
                .reviewId(review.getId())
                .content(review.getContent())
                .rating(review.getRating())
                .imageUrls(List.of())
                .build();
    }

    public static ReviewResDTO.GetMyReview toGetMyReview(Review review){
        return ReviewResDTO.GetMyReview.builder()
                .reviewId(review.getId())
                .content(review.getContent())
                .rating(review.getRating())
                .build();
    }

    public static <T> ReviewResDTO.PaginationCursor<T> toPaginationCursor(
            List<T> data,
            Boolean hasNext,
            String nextCursor,
            Integer pageSize
    ) {
        return ReviewResDTO.PaginationCursor.<T>builder()
                .data(data)
                .hasNext(hasNext)
                .nextCursor(nextCursor)
                .pageSize(pageSize)
                .build();
    }
}
