package umc.term.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum TermName {
    SERVICE_TERMS("서비스 이용약관"),
    PRIVACY_POLICY("개인정보 수집 및 이용"),
    LOCATION_TERMS("위치정보 이용약관");

    private final String label;
}
