package umc.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.member.converter.MemberConverter;
import umc.member.dto.MemberReqDTO;
import umc.member.dto.MemberResDTO;
import umc.member.entity.Member;
import umc.member.exception.MemberException;
import umc.member.exception.code.MemberErrorCode;
import umc.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final MemberRepository memberRepository;

    public MemberResDTO.GetInfo getInfo(MemberReqDTO.GetInfo dto) {
        Long memberId = dto.id();

        Member member =  memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));

        return MemberConverter.toGetInfo(member);
    }

    public MemberResDTO.AuthResDTO.SignUpResultDTO signUp(MemberReqDTO.SingUpDTO request) {
    }
}
