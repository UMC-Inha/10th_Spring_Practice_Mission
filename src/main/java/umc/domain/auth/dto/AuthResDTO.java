package umc.domain.auth.dto;

import lombok.Builder;

import java.time.LocalDate;

public class AuthResDTO {

    @Builder
    public record SignUpDTO(
            Long memberId,
            String email,
            String name,
            LocalDate birth,
            String address
    ) {}
}
