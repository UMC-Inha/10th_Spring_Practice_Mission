package umc.domain.review.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ReviewReqDTO {

    public record CreateReview(
            String content,
            Integer rating,
            List<MultipartFile> images
    ) {}
}
