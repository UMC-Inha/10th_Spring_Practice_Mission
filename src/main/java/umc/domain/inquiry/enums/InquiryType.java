package umc.domain.inquiry.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum InquiryType {
    COMPLAINT("불만 접수"),
    SUGGESTION("서비스 제안"),
    PAYMENT("결제 문의"),
    ETC("기타");

    private final String description;
}
