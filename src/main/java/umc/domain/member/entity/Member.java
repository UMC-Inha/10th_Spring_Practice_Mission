package umc.domain.member.entity;

import jakarta.persistence.*;
import lombok.*;
import umc.domain.member.entity.mapping.MemberFood;
import umc.domain.member.entity.mapping.MemberTerm;
import umc.domain.member.enums.Gender;
import umc.domain.member.enums.SocialType;
import umc.domain.mission.entity.mapping.MemberMission;
import umc.domain.review.entity.Review;
import umc.global.entity.BaseEntity;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "nickname")
    private String nickname;

    @Column(name = "email")
    private String email;

    @Column(name = "phone_number")
    private String phoneNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "gender", nullable = false)
    private Gender gender;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "address")
    private String address;

    @Column(name = "points")
    private Integer points;

    @Column(name = "profile_url")
    private String profileUrl;

    @Enumerated(EnumType.STRING)
    @Column(name = "social_type")
    private SocialType socialType;

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberTerm> memberTerms = new ArrayList<>();

    @OneToMany(mappedBy = "member", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<MemberFood> memberFoods = new ArrayList<>();
}
