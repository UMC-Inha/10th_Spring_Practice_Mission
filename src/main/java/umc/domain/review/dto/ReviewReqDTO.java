package umc.domain.review.dto;

import java.util.List;

public class ReviewReqDTO {

    public record CreateReviewReq(
            double rate,
            String content,
            List<String> imageUrls
    ){}

    public record MyReview(
      Long id,
      String cursor,
      Integer pageSize,
      String sort
    ){}

}
