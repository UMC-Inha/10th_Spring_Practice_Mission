package umc.domain.store.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum StoreType {

    KOREAN("한식"),
    CHINESE("중식"),
    JAPANESE("일식"),
    WESTERN("양식"),
    CAFE("카페"),
    FAST_FOOD("패스트푸드"),
    BAR("술집"),
    ETC("기타");

    private final String description;
}
