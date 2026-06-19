package com.example.umc10th.domain.member.service;

import com.example.umc10th.domain.member.converter.MemberConverter;
import com.example.umc10th.domain.member.dto.MemberReqDTO;
import com.example.umc10th.domain.member.dto.MemberResDTO;
import com.example.umc10th.domain.member.entity.Member;
import com.example.umc10th.domain.member.exception.MemberErrorCode;
import com.example.umc10th.domain.member.repository.MemberRepository;
import com.example.umc10th.global.apiPayload.exception.ProjectException;
import com.example.umc10th.global.security.entity.AuthMember;
import com.example.umc10th.global.security.util.JwtUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
   private final JwtUtil jwtUtil;

   @Transactional
    public MemberResDTO.SignUp signUp(MemberReqDTO.SignUp dto) {
       String encodedPassword = passwordEncoder.encode(dto.password());

        Member member = MemberConverter.toMember(dto, encodedPassword);

        Member saved = memberRepository.save(member);
        return MemberConverter.toSignUpRes(saved);
    }

    @Transactional
    public MemberResDTO.Login login(MemberReqDTO.Login dto){
       Member member = memberRepository.findByEmail(dto.email())
           .orElseThrow(()-> new ProjectException(MemberErrorCode.MEMBER_NOT_FOUND));

       if(!passwordEncoder.matches(dto.password(), member.getPassword())){
          throw new ProjectException(MemberErrorCode.INVALID_PASSWORD);
       }

       AuthMember authMember = new AuthMember(member);

       String accessToken = jwtUtil.createAccessToken(authMember);

       return MemberConverter.toLoginRes(member, accessToken);
    }

//    @Transactional(readOnly = true)
//    public MemberResDTO.MyPage getMyPage(Long memberId) {
//        Member member = memberRepository.findById(memberId)
//            .orElseThrow(() -> new ProjectException(MemberErrorCode.MEMBER_NOT_FOUND));
//        return MemberConverter.toMyPageRes(member);
//    }

   @Transactional(readOnly = true)
    public MemberResDTO.MyPage getMyPage(AuthMember member) {
       return MemberConverter.toMyPageRes(member.getMember());
    }
}