package umc.domain.member.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum SocialType {

    KAKAO("카카오"),
    NAVER("네이버"),
    GOOGLE("구글"),
    APPLE("애플");

    private final String description;
}
