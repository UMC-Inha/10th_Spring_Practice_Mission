package umc.global.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import umc.domain.auth.exception.AuthException;
import umc.domain.auth.exception.code.AuthErrorCode;
import umc.domain.member.converter.MemberConverter;
import umc.domain.member.entity.Member;
import umc.domain.member.enums.SocialType;
import umc.domain.member.repository.MemberRepository;
import umc.global.security.dto.KakaoDTO;
import umc.global.security.dto.OAuthDTO;
import umc.global.security.entity.OAuthMember;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final MemberRepository memberRepository;

    @Override
    public OAuth2User loadUser(
            OAuth2UserRequest userRequest
    ) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);

        SocialType providerId;
        String socialUid;
        Map<String, Object> attributes = oAuth2User.getAttribute("kakao_account");
        Map<String, Object> profile = (Map<String, Object>) attributes.get("profile");
        try {
            providerId = SocialType.valueOf(userRequest.getClientRegistration().getRegistrationId().toUpperCase());
            socialUid = String.valueOf((Long) oAuth2User.getAttribute("id"));
        } catch (IllegalArgumentException e) {
            throw new AuthException(AuthErrorCode.NOT_SUPPORT_SOCIAL_PROVIDER);
        }

        OAuthDTO dto;
        switch (providerId) {
            case KAKAO -> {
                String email = attributes.get("email").toString();
                String name = profile.get("nickname").toString();
                dto = new KakaoDTO(socialUid, email, name);
            }
            default -> throw new AuthException(AuthErrorCode.NOT_SUPPORT_SOCIAL_PROVIDER);
        }

        Member member = memberRepository.findBySocialTypeAndSocialUid(providerId, socialUid)
                .orElseGet(() -> {
                    Member newMember = MemberConverter.toMember(dto);
                    memberRepository.save(newMember);
                    return newMember;
                });
        return new OAuthMember(member, oAuth2User.getAttributes());
    }
}
