package umc.domain.auth.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.auth.converter.AuthConverter;
import umc.domain.auth.dto.AuthReqDTO;
import umc.domain.auth.dto.AuthResDTO;
import umc.domain.auth.exception.AuthException;
import umc.domain.auth.exception.code.AuthErrorCode;
import umc.domain.member.entity.Food;
import umc.domain.member.entity.Member;
import umc.domain.member.entity.Term;
import umc.domain.member.repository.FoodRepository;
import umc.domain.member.repository.MemberRepository;
import umc.domain.member.repository.TermRepository;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class AuthService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final TermRepository termRepository;
    private final FoodRepository foodRepository;

    @Transactional
    public AuthResDTO.SignUpDTO signUp(AuthReqDTO.SignUpDTO reqDto) {
        memberRepository.findByEmail(reqDto.email()).ifPresent(member -> {
            throw new AuthException(AuthErrorCode.DUPLICATED_EMAIL);
        });

        String encodedPassword = passwordEncoder.encode(reqDto.password());
        Member member = AuthConverter.toMember(reqDto, encodedPassword);

        addTermsToMember(member, reqDto.terms());
        addFoodsToMember(member, reqDto.foodPreferences());

        memberRepository.save(member);
        return AuthConverter.toSignUpDTO(member);
    }

    private void addTermsToMember(Member member, List<AuthReqDTO.SignUpDTO.TermDTO> termDTOs) {
        List<Term> allTerms = termRepository.findAll();

        Set<Long> allTermIds = allTerms.stream()
                .map(Term::getId)
                .collect(Collectors.toSet());

        Set<Long> requestedTermIds = termDTOs.stream()
                .map(AuthReqDTO.SignUpDTO.TermDTO::termId)
                .collect(Collectors.toSet());

        if (!allTermIds.containsAll(requestedTermIds)) {
            throw new AuthException(AuthErrorCode.TERMS_MISMATCH);
        }

        Map<Long, Term> terms = allTerms.stream()
                .collect(Collectors.toMap(Term::getId, term -> term));

        termDTOs.forEach(termDTO -> {
            Term term = terms.get(termDTO.termId());
            if (term.getIsRequired() && !termDTO.isAgreed()) {
                throw new AuthException(AuthErrorCode.REQUIRED_TERM_NOT_AGREED);
            }
            member.addMemberTerm(term, termDTO.isAgreed());
        });
    }

    private void addFoodsToMember(Member member, List<AuthReqDTO.SignUpDTO.FoodPreferenceDTO> foodDTOs) {
        foodDTOs.forEach(foodDTO -> {
            Food food = foodRepository.findById(foodDTO.foodId())
                    .orElseThrow(() -> new AuthException(AuthErrorCode.INVALID_FOOD));
            member.addPreferenceFood(food);
        });
    }
}
