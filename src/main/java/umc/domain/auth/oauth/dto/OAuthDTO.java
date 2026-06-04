package umc.domain.auth.oauth.dto;

import umc.domain.member.enums.SocialType;

public interface OAuthDTO {
    SocialType getSocialType();
    String getSocialUid();
    String getEmail();
    String getName();
}
