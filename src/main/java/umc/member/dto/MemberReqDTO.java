package umc.member.dto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import umc.member.enums.SocialType;

import java.time.LocalDate;
import java.util.List;

public class MemberReqDTO {

    public record GetInfo(
            Long id
    ){}

    public record SingUpDTO(
            @NotBlank(message = "이메일은 필수 입력 값입니다.")
            @Email(message = "올바른 이메일 형식이 아닙니다.")
            String email,
            @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
            String password,
            @NotBlank(message = "이름은 필수 입력 값입니다.")
            String name,
            @NotBlank(message = "성별은 필수 입력 값입니다.")
            String gender,
            @NotBlank(message = "생년월일은 필수 입력 값입니다.")
            LocalDate birth,
            @NotBlank(message = "주소는 필수 입력 값입니다.")
            String address,
            String socialUid,
            String socialType,
            List<Long> agreedTerms,
            List<Long> userFoods
    ) {}

    public record LoginDTO(
            @NotBlank(message = "이메일은 필수 입력 값입니다.")
            @Email(message = "올바른 이메일 형식이 아닙니다.")
            String email,
            @NotBlank(message = "비밀번호는 필수 입력 값입니다.")
            String password
    ) {}
}
