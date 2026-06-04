package umc.global.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.OAuth2Error;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;
import umc.domain.auth.exception.AuthException;
import umc.domain.auth.oauth.OAuthAttributeMissingException;
import umc.domain.auth.oauth.OAuthAttributes;
import umc.domain.auth.oauth.OAuthMemberService;
import umc.domain.auth.oauth.dto.OAuthDTO;
import umc.domain.member.entity.Member;
import umc.global.security.entity.OAuthMember;

@Service
@RequiredArgsConstructor
public class CustomOAuth2UserService extends DefaultOAuth2UserService {

    private final OAuthMemberService oAuthMemberService;

    @Override
    public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
        OAuth2User oAuth2User = super.loadUser(userRequest);
        String registrationId = userRequest.getClientRegistration().getRegistrationId();

        try {
            OAuthDTO oAuthDto = OAuthAttributes.of(registrationId, oAuth2User.getAttributes());
            Member member = oAuthMemberService.loadOrCreate(oAuthDto);
            return new OAuthMember(member, oAuth2User.getAttributes());
        } catch (OAuthAttributeMissingException e) {
            throw toOAuth2Exception(e, " (누락: " + e.getAttribute() + ")");
        } catch (AuthException e) {
            throw toOAuth2Exception(e, "");
        }
    }

    private OAuth2AuthenticationException toOAuth2Exception(AuthException e, String suffix) {
        OAuth2Error error = new OAuth2Error(e.getCode().getCode());
        return new OAuth2AuthenticationException(error, e.getCode().getMessage() + suffix, e);
    }
}
