package umc.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import umc.domain.member.controller.MemberController;
import umc.domain.member.converter.MemberConverter;
import umc.domain.member.dto.MemberReqDTO;
import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.entity.Member;
import umc.domain.member.exception.MemberException;
import umc.domain.member.exception.code.MemberErrorCode;
import umc.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResDTO.MyPageResDTO getInfo(MemberReqDTO.MyPageReqDTO dto) {
        Long memberId = dto.id();
        Member member = memberRepository.findById(memberId).orElseThrow(
                ()->new MemberException(MemberErrorCode.MEMBER_NOT_FOUND)
        );

        return MemberConverter.toGetInfo(member);
    }

    public MemberResDTO.PointResDTO getPoint(Long id) {
        Member member = memberRepository.findById(id).orElseThrow(
                ()->new MemberException(MemberErrorCode.MEMBER_NOT_FOUND)
        );

        return MemberConverter.toGetPoint(member);
    }
}
