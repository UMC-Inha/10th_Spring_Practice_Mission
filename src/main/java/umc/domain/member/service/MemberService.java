package umc.domain.member.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import umc.domain.member.converter.MemberConverter;
import umc.domain.member.dto.MemberReqDTO;
import umc.domain.member.dto.MemberResDTO;
import umc.domain.member.entity.Member;
import umc.domain.member.enums.Gender;
import umc.domain.member.enums.Org_cd;
import umc.domain.member.enums.Status;
import umc.domain.member.exception.MemberException;
import umc.domain.member.exception.code.MemberErrorCode;
import umc.domain.member.repository.MemberRepository;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberResDTO.GetInfo getInfo(MemberReqDTO.GetInfo dto) {
        Long memberId = dto.member_id();
        Member member = memberRepository.findById(memberId)
                .orElseThrow(() -> new MemberException(MemberErrorCode.MEMBER_NOT_FOUND));
        return MemberConverter.toGetInfo(member);
    }

    public void createMember() {
        Member member = Member.builder()
                .log_id("user123")
                .email("test@gmail.com")
                .password("1234")
                .name("홍길동")
                .gender(Gender.MALE)
                .birth("19990101")
                .post("123456")
                .add1("서울시 강남구")
                .add2("101호")
                .phone("01012345678")
                .point(0)
                .status(Status.ACTIVE)
                .org_cd(Org_cd.GOOGLE)
                .build();

        memberRepository.save(member); // ← 여기서 INSERT 실행
    }
}