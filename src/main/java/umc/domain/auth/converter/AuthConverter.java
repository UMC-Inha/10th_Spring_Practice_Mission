package umc.domain.auth.converter;

import umc.domain.auth.dto.AuthReqDTO;
import umc.domain.auth.dto.AuthResDTO;
import umc.domain.member.entity.Member;

import java.time.LocalDate;

public class AuthConverter {

    public static Member toMember(AuthReqDTO.SignUpDTO reqDto, String encodedPassword) {
        return Member.builder()
                .email(reqDto.email())
                .password(encodedPassword)
                .name(reqDto.name())
                .birth(LocalDate.parse(reqDto.birth()))
                .gender(reqDto.gender())
                .address(reqDto.address())
                .build();
    }

    public static AuthResDTO.SignUpDTO toSignUpDTO(Member member) {
        return AuthResDTO.SignUpDTO.builder()
                .memberId(member.getId())
                .email(member.getEmail())
                .name(member.getName())
                .birth(member.getBirth())
                .address(member.getAddress())
                .build();
    }
}
