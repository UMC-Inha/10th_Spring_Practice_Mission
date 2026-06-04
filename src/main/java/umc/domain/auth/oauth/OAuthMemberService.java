package umc.domain.auth.oauth;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.auth.oauth.dto.OAuthDTO;
import umc.domain.member.converter.MemberConverter;
import umc.domain.member.entity.Member;
import umc.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class OAuthMemberService {

    private final MemberRepository memberRepository;

    @Transactional
    public Member loadOrCreate(OAuthDTO oAuthDto) {
        return memberRepository.findBySocialTypeAndSocialUid(oAuthDto.getSocialType(), oAuthDto.getSocialUid())
                .orElseGet(() -> memberRepository.save(MemberConverter.toMember(oAuthDto)));
    }
}
