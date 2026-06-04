package umc.domain.auth.oauth;

import umc.domain.auth.exception.AuthException;
import umc.domain.auth.exception.code.AuthErrorCode;
import umc.domain.auth.oauth.dto.KakaoDTO;
import umc.domain.auth.oauth.dto.OAuthDTO;
import umc.domain.member.enums.SocialType;

import java.util.Map;

public class OAuthAttributes {

    public static OAuthDTO of(String registrationId, Map<String, Object> rawAttributes) {
        SocialType provider;
        try {
            provider = SocialType.valueOf(registrationId.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new AuthException(AuthErrorCode.NOT_SUPPORT_SOCIAL_PROVIDER);
        }
        return switch (provider) {
            case KAKAO -> ofKakao(rawAttributes);
            default -> throw new AuthException(AuthErrorCode.NOT_SUPPORT_SOCIAL_PROVIDER);
        };
    }

    private static OAuthDTO ofKakao(Map<String, Object> rawAttributes) {
        String id = requireString(rawAttributes, "id");

        Map<String, Object> kakaoAccount = requireMap(rawAttributes, "kakao_account");
        String email = requireString(kakaoAccount, "email");

        Map<String, Object> profile = requireMap(kakaoAccount, "profile");
        String nickname = requireString(profile, "nickname");

        return new KakaoDTO(id, email, nickname);
    }

    private static String requireString(Map<String, Object> map, String key) {
        Object value = map.get(key);
        if (value == null || value.toString().isBlank()) {
            throw new OAuthAttributeMissingException(key);
        }
        return value.toString();
    }

    @SuppressWarnings("unchecked")
    private static Map<String, Object> requireMap(Map<String, Object> map, String key) {
        Object value = map.get(key);
        if (!(value instanceof Map<?, ?>)) {
            throw new OAuthAttributeMissingException(key);
        }
        return (Map<String, Object>) value;
    }
}
