package umc.term.enums;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Getter
@RequiredArgsConstructor
public enum TermName {
    SERVICE_TERMS("서비스 이용약관", true),
    PRIVACY_POLICY("개인정보 수집 및 이용", true),
    LOCATION_TERMS("위치정보 이용약관", false),
    MARKETING_TERMS("마케팅 수신 동의", false);

    private final String label;
    private final boolean isRequired;

    public static List<TermName> getRequiredTermNames() {
        return Arrays.stream(values())
                .filter(TermName::isRequired)
                .toList();
    }
}
