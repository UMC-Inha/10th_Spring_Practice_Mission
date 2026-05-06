package umc.domain.member.entity;


import jakarta.persistence.*;
import lombok.*;
import umc.global.apiPayload.code.BaseEntity;

import java.time.LocalDate;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor
@Builder
@Table(name="members")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String nickname;

    private LocalDate birth;

    private String address;

    private String email;

    private Integer point;

    private String phoneNumber;

    private Boolean phoneVerified;

    private String profileUrl;


}
