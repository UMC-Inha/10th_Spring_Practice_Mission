package umc.global.security.dto;

import lombok.RequiredArgsConstructor;
import umc.domain.member.enums.SocialProvider;

@RequiredArgsConstructor
public class KakaoDTO implements OAuthDTO{

    private final String id;
    private final String email;
    private final String name;

    @Override
    public SocialProvider getSocialProvider(){
        return SocialProvider.KAKAO;
    }

    @Override
    public String getSocialId(){
        return id;
    }

    @Override
    public String getSocialEmail(){
        return email;
    }

    @Override
    public String getName(){
        return name;
    }


}
