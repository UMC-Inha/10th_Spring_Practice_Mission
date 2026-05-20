package umc.member.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import umc.food.entity.Food;
import umc.food.exeption.FoodException;
import umc.food.exeption.code.FoodErrorCode;
import umc.food.repository.FoodRepository;
import umc.member.converter.MemberConverter;
import umc.member.dto.MemberReqDTO;
import umc.member.dto.MemberResDTO;
import umc.member.entity.Member;
import umc.member.entity.mapping.MemberFood;
import umc.member.entity.mapping.MemberTerm;
import umc.member.enums.Gender;
import umc.member.enums.SocialType;
import umc.member.exception.MemberException;
import umc.member.exception.code.MemberErrorCode;
import umc.member.repository.MemberFoodRepository;
import umc.member.repository.MemberRepository;
import umc.member.repository.MemberTermRepository;
import umc.term.entity.Term;
import umc.term.exception.TermException;
import umc.term.exception.code.TermErrorCode;
import umc.term.repository.TermRepository;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final MemberTermRepository memberTermRepository;
    private final MemberFoodRepository memberFoodRepository;
    private final FoodRepository foodRepository;
    private final TermRepository termRepository;
    private final PasswordEncoder passwordEncoder;

    public MemberResDTO.GetInfo getInfo(MemberReqDTO.GetInfo dto) {
        Long memberId = dto.id();

        Member member =  memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberConverter.toGetInfo(member);
    }

    @Transactional
    public MemberResDTO.AuthResDTO.SignUpResultDTO signUp(MemberReqDTO.SingUpDTO request) {

        Gender gender = Arrays.stream(Gender.values())
                .filter(g -> g.name().equalsIgnoreCase(request.gender()))
                .findFirst()
                .orElseThrow(() -> new MemberException(MemberErrorCode.INVALID_GENDER_TYPE));

        SocialType socialType = Arrays.stream(SocialType.values())
                .filter(s -> s.name().equalsIgnoreCase(request.socailType()))
                .findFirst()
                .orElseThrow(() -> new MemberException(MemberErrorCode.INVALID_SOCIAL_TYPE));

        if(memberRepository.findByEmail(request.email()).isPresent()){
            throw new MemberException(MemberErrorCode.DUPLICATE_EMAIL);
        }

        String encordPassword = passwordEncoder.encode(request.password());

        Member newMember = MemberConverter.toMember(request, encordPassword);

        List<Food> foodList = request.userFoods().stream()
                .map(foodId -> foodRepository.findById(foodId)
                        .orElseThrow(() -> new FoodException(FoodErrorCode.FOOD_NOT_FOUND)))
                .toList();

        List<MemberFood> memberFoodList = MemberConverter.toMemberFoodList(foodList);
        memberFoodList.forEach(memberFood -> memberFood.mappingMember(newMember));

        List<Term> termList = request.agreedTerms().stream()
                .map(termId -> termRepository.findById(termId)
                        .orElseThrow(() -> new TermException(TermErrorCode.TERM_NOT_FOUND)))
                .toList();

        List<MemberTerm> memberTermList = MemberConverter.toMemberTermList(termList);
        memberTermList.forEach(memberTerm -> memberTerm.mappingMember(newMember));

        Member savedMember = memberRepository.save(newMember);

        memberFoodRepository.saveAll(memberFoodList);
        memberTermRepository.saveAll(memberTermList);

        return MemberConverter.toSignUpResultDTO(savedMember);
    }
}
