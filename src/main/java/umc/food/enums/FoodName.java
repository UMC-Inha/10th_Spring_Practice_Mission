package umc.food.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum FoodName {
    KOREAN("한식"),
    JAPANESE("일식"),
    CHINESE("중식"),
    WESTERN("양식"),
    CHICKEN("치킨");

    private final String label;
}
