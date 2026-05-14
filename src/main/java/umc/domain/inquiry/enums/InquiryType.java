package umc.domain.inquiry.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum InquiryType {

    ACCOUNT("계정 문의"),
    PAYMENT("결제 문의"),
    BUG("버그 신고"),
    ETC("기타");

    private final String description;
}
