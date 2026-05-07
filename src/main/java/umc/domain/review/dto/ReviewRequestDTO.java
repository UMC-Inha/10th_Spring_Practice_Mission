package umc.domain.review.dto;

import java.math.BigDecimal;

public class ReviewRequestDTO {


    public record Create(
            BigDecimal star,
            String content
    ) {}

}
