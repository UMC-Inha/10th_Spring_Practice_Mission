package umc.member.service;

import lombok.RequiredArgsConstructor;
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
import umc.member.exception.MemberException;
import umc.member.exception.code.MemberErrorCode;
import umc.member.repository.MemberRepository;
import umc.term.repository.TermRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;
    private final FoodRepository foodRepository;
    private final TermRepository termRepository;

    public MemberResDTO.GetInfo getInfo(MemberReqDTO.GetInfo dto) {
        Long memberId = dto.id();

        Member member =  memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberConverter.toGetInfo(member);
    }

    public MemberResDTO.AuthResDTO.SignUpResultDTO signUp(MemberReqDTO.SingUpDTO request) {
        Member newMember = MemberConverter.toMember(request);

        List<Food> foodList = request.userFoods().stream()
                .map(foodId -> foodRepository.findById(foodId)
                        .orElseThrow(() -> new FoodException(FoodErrorCode.FOOD_NOT_FOUND)))
                .toList();

        List<MemberFood> memberFoodList = MemberConverter.toMemberFoodList(foodList);
        memberFoodList.forEach(memberFood -> memberFood.setMember(newMember));

        Member savedMember = memberRepository.save(newMember);

        return MemberConverter.toSignUpResultDTO(savedMember);
    }
}
