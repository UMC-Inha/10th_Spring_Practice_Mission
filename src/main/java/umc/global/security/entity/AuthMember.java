package umc.global.security.entity;

import jakarta.annotation.Nullable;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import umc.domain.member.entity.Member;

import java.io.Serializable;
import java.util.Collection;
import java.util.List;

@Getter
@RequiredArgsConstructor
public class AuthMember implements UserDetails {

    private final Member member;

    //현재 로그인한 사용자의 권한을 반환하는 함수(빈 리스트->권한 없음)
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public @Nullable String getPassword() {
        return member.getPassword();
    }

    @Override
    public String getUsername() {
        return member.getEmail();
    }
}
