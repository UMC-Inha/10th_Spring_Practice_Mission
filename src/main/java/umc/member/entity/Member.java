package umc.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.apiPayload.entity.BaseEntity;
import umc.member.entity.mapping.MemberFood;
import umc.member.entity.mapping.MemberTerm;
import umc.member.enums.Gender;
import umc.member.enums.SocialType;
import umc.mission.entity.Mapping.MemberMission;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "member")
public class Member extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth", nullable = false)
    private LocalDate birth;

    @Column(name = "address", nullable = false)
    private String address;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "point", nullable = false)
    private Integer point;

    @Column(name = "profileUrl")
    private String profileUrl;

    @Column(name = "phoneNumber")
    private String phoneNumber;

    @Column(name = "socialUid", nullable = false)
    private String socialUid;

    @Column(name = "socialType", nullable = false)
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<MemberFood> memberFoodList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<MemberTerm> memberTermList = new ArrayList<>();

    @Builder.Default
    @OneToMany(mappedBy = "member", cascade = CascadeType.REMOVE)
    private List<MemberMission> memberMissionList = new ArrayList<>();
}
