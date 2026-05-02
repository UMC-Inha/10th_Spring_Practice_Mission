package umc.domain.member.dto;

import lombok.Builder;

public class MemberResDTO {

    //마이페이지
    @Builder
    public record getInfo(
            int memberId,
            String name,
            String email,
            String phoneNumber,
            int Point
    ){}

    //보유 포인트 조회
    @Builder
    public record getPoint(
            int Point
    ){}
}
