package umc.domain.review.dto;

import java.util.List;

public class ReviewReqDTO {

    public record ReviewReq(
            double rate,
            String content,
            List<String> imageUrls
    ){}

}
