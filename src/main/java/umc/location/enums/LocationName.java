package umc.location.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import umc.location.exception.LocationException;
import umc.location.exception.code.LocationErrorCode;

@Getter
@RequiredArgsConstructor
public enum LocationName {
    ANAM_DONG("안암동"),
    SUNGYONG_DONG("성북동"),
    JIGI_DONG("제기동"),
    SINCHON_DONG("신촌동"),
    GANGNAM_DONG("역삼동");

    private final String label;

    public static LocationName fromLabel(String label) {
        for (LocationName location : LocationName.values()) {
            if (location.getLabel().equals(label)) {
                return location;
            }
        }
        throw new LocationException(LocationErrorCode.LOCATION_LABEL_ERROR_CODE);
    }
}
