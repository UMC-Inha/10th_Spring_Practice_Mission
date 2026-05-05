package umc.domain.review.dto;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public class ReviewReqDTO {

    public record ReviewCreateDTO(
            String reviewContent,
            Integer starRating,
            List<MultipartFile> images
    ) {}
}
