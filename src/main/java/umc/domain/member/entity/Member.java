package umc.domain.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.domain.member.enums.Gender;
import umc.domain.member.enums.SocialProvider;
import umc.global.entity.BaseEntity;

import java.time.LocalDate;

@Builder
@Entity
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nickname", nullable = false)
    private String nickname;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private Gender gender = Gender.NONE;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "current_point", nullable = false)
    private Long currentPoint;

    @Column(name = "social_provider", nullable = false)
    @Enumerated(EnumType.STRING)
    @Builder.Default
    private SocialProvider socialProvider = SocialProvider.LOCAL;

    @Column(name = "social_id")
    private String socialId;

    @Column(name = "email")
    private String email;

}
