package umc.domain.member.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import umc.domain.member.enums.Gender;
import umc.domain.member.enums.SocialType;
import umc.global.apiPayload.code.BaseEntity;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Table(name = "member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @Column(name = "gender", nullable = false)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth")
    private LocalDate birth;

    @Column(name = "address", nullable = false, length = 200)
    private String address;

    @Column(name = "email", nullable = false, length = 100)
    private String email;

    @Column(name = "point", nullable = false)
    private Integer point;

    @Column(name = "phone_number", length = 20)
    private String phoneNumber;

    @Column(name = "phone_verified", nullable = false)
    private Boolean isPhoneVerified;

    @Column(name = "social_provider", nullable = false, length = 20)
    @Enumerated(EnumType.STRING)
    private SocialType socialType;

    @Column(name = "social_uid", nullable = false, length = 200)
    private String socialUid;

    @Column(name = "profile_url")
    private String profileUrl;
}