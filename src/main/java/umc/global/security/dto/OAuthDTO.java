package umc.global.security.dto;

import umc.domain.member.enums.SocialProvider;

public interface OAuthDTO {
    SocialProvider getSocialProvider();
    String getSocialId();
    String getSocialEmail();
    String getName();
}
