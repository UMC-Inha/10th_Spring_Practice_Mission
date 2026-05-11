package umc.domain.inquiry.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InquiryStatus {
    PENDING("답변 대기"),
    RESOLVED("답변 완료");

    private final String description;
}
