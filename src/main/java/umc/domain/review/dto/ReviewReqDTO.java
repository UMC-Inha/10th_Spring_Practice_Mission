package umc.domain.review.dto;

import lombok.Getter;

public class ReviewReqDTO {
	@Getter
	public static class ReviewCreateDTO {
		String reviewContent;
		Integer star;
		String photoUrl;
	}
}
