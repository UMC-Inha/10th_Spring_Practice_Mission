package umc.domain.auth.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.auth.converter.AuthConverter;
import umc.domain.auth.dto.AuthReqDTO;
import umc.domain.auth.dto.AuthResDTO;
import umc.domain.auth.exception.AuthException;
import umc.domain.auth.exception.code.AuthErrorCode;
import umc.domain.member.entity.Member;
import umc.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;

    @Transactional
    public AuthResDTO.SignUpDTO signUp(AuthReqDTO.SignUpDTO reqDto) {
        memberRepository.findByEmail(reqDto.email()).ifPresent(member -> {
            throw new AuthException(AuthErrorCode.DUPLICATED_EMAIL);
        });

        String encodedPassword = passwordEncoder.encode(reqDto.password());
        Member member = AuthConverter.toMember(reqDto, encodedPassword);

        memberRepository.save(member);
        return AuthConverter.toSignUpDTO(member);
    }
}
