package umc.member.converter;

import umc.food.entity.Food;
import umc.member.dto.MemberReqDTO;
import umc.member.dto.MemberResDTO;
import umc.member.entity.Member;
import umc.member.entity.mapping.MemberFood;
import umc.member.entity.mapping.MemberTerm;
import umc.member.enums.Gender;
import umc.member.enums.SocialType;
import umc.term.entity.Term;

import java.util.List;
import java.util.stream.Collectors;

public class MemberConverter {
    public static MemberResDTO.GetInfo toGetInfo(
            Member member
    ){
        return MemberResDTO.GetInfo.builder()
                .email(member.getEmail())
                .name(member.getName())
                .point(member.getPoint())
                .profileUrl(member.getProfileUrl())
                .phoneNumber((member.getPhoneNumber()))
                .build();
    }

    public static Member toMember(MemberReqDTO.SingUpDTO request){
        return Member.builder()
                .email(request.email())
                .name(request.name())
                .gender(Gender.valueOf(request.gender()))
                .birth(request.birth())
                .address(request.address())
                .socialUid(request.socailUid())
                .socialType(SocialType.valueOf(request.socailType()))
                .point(0)
                .build();
    }

    public static List<MemberFood> toMemberFoodList(List<Food> foodList) {
        return foodList.stream()
                .map(food -> MemberFood.builder()
                        .food(food)
                        .build())
                .collect(Collectors.toList());
    }

    public static List<MemberTerm> toMemberTermList(List<Term> termList) {
        return termList.stream()
                .map(term -> MemberTerm.builder()
                        .term(term)
                        .build())
                .collect(Collectors.toList());
    }

    public static MemberResDTO.AuthResDTO.SignUpResultDTO toSignUpResultDTO(Member member) {
        return MemberResDTO.AuthResDTO.SignUpResultDTO.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .gender(member.getGender().toString())
                .address(member.getAddress())
                .birth(member.getBirth().toString())
                .socialType(member.getSocialType() != null ? member.getSocialType().toString() : null)
                .createdAt(member.getCreatedAt())
                .build();
    }
}
