package umc.domain.inquiry.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ReplyStatus {

    WAITING("답변 대기"),
    CANCELED("문의 취소"),
    COMPLETED("답변 완료");

    private final String description;
}
