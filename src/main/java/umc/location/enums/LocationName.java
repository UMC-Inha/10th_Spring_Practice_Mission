package umc.location.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum LocationName {
    ANAM_DONG("안암동"),
    SUNGYONG_DONG("성북동"),
    JIGI_DONG("제기동"),
    SINCHON_DONG("신촌동"),
    GANGNAM_DONG("역삼동");

    private final String label;
}
