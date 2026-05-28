package umc.domain.member.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import umc.domain.member.converter.MemberConverter;
import umc.domain.member.dto.MemberReqDTO;
import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.entity.Food;
import umc.domain.member.entity.Member;
import umc.domain.member.entity.Term;
import umc.domain.member.entity.mapping.MemberFood;
import umc.domain.member.entity.mapping.MemberTerm;
import umc.domain.member.exception.FoodException;
import umc.domain.member.exception.MemberException;
import umc.domain.member.exception.TermException;
import umc.domain.member.exception.code.FoodErrorCode;
import umc.domain.member.exception.code.MemberErrorCode;
import umc.domain.member.exception.code.TermErrorCode;
import umc.domain.member.repository.*;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final TermRepository termRepository;
    private final MemberTermRepository memberTermRepository;
    private final FoodRepository foodRepository;
    private final MemberFoodRepository memberFoodRepository;

    @Transactional(readOnly = true)
    public MemberResDTO.MyPageResDTO getInfo(MemberReqDTO.MyPageReqDTO dto) {
        Long memberId = dto.id();
        Member member = memberRepository.findById(memberId).orElseThrow(
                ()->new MemberException(MemberErrorCode.MEMBER_NOT_FOUND)
        );

        return MemberConverter.toGetInfo(member);
    }

    @Transactional(readOnly = true)
    public MemberResDTO.PointResDTO getPoint(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(
                ()->new MemberException(MemberErrorCode.MEMBER_NOT_FOUND)
        );

        return MemberConverter.toGetPoint(member);
    }

    @Transactional
    public MemberResDTO.SignUpRes signUp(MemberReqDTO.@Valid SignUpReq dto) {

        //이메일 중복 검증
        if(memberRepository.existsByEmail(dto.email())){
            throw new MemberException(MemberErrorCode.MEMBER_ALREADY_EXISTS);
        }

        //동의한 정책이 없으면 null
        List<Long> memberTerm = (dto.agreedTermsIds()!=null)?dto.agreedTermsIds():List.of();

        //유효한 정책 id 목록
        List<Long> validTermId =termRepository.findAllIds();

        //받은 정책 id가 유요한 정책 id인지 검사
        if (!validTermId.containsAll(memberTerm)) {
            throw new TermException(TermErrorCode.INVALID_TERM_ID);
        }

        //필수 정책 id 목록
        List<Long> requiredTermId = termRepository.findAllByRequired(true)
                .stream()
                .map(Term::getId)
                .toList();

        //동의한 정책 id가 필수 정책을 모두 포함 하는지 검사
        if (!memberTerm.containsAll(requiredTermId)) {
            throw new TermException(TermErrorCode.REQUIRED_TERM_NOT_AGREED);
        }

        //선호 음식 검증
        List<Long> validFoodId = foodRepository.findAllIds();
        if (!validFoodId.containsAll(dto.userFood())) {
            throw new FoodException(FoodErrorCode.INVALID_FOOD_ID);
        }

        //비밀 번호 암호화
        String encodedPassword = bCryptPasswordEncoder.encode(dto.password());

        //사용자 db에 저장
        Member newMember = MemberConverter.toMemberEntity(dto, encodedPassword);
        Member savedMember = memberRepository.save(newMember);

        //사용자 정책 db에 저장
        List<MemberTerm> memberTermList = memberTerm
                .stream()
                .map( termId -> {
                    Term term = termRepository.findById(termId)
                            .orElseThrow(() -> new TermException(TermErrorCode.INVALID_TERM_ID));

                    return MemberTerm.builder()
                            .member(newMember)
                            .term(term)
                            .build();
                }).toList();

        memberTermRepository.saveAll(memberTermList);

        //선호 음식 db에 저장
        List<MemberFood> memberFoodList = dto.userFood()
                .stream()
                .map( foodId -> {
                    Food food = foodRepository.findById(foodId)
                            .orElseThrow(() -> new FoodException(FoodErrorCode.INVALID_FOOD_ID));

                    return MemberFood.builder()
                            .member(newMember)
                            .food(food)
                            .build();
                }).toList();

        memberFoodRepository.saveAll(memberFoodList);

        return MemberConverter.toSignUpRes(savedMember);
    }
}
