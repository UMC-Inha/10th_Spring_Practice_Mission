package umc.domain.member.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import umc.domain.common.BaseEntity;
import umc.domain.member.enums.Gender;
import umc.domain.member.enums.Org_cd;
import umc.domain.member.enums.Status;


@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name="member")
public class Member extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id", nullable = false)
    private Long id;

    @Column(name = "log_id", nullable = false, length = 50)
    private String log_id;

    @Column(name = "email", nullable = false, length = 50)
    private String email;

    @Column(name = "password", nullable = false, length = 250)
    private String password;

    @Column(name = "name", nullable = false, length = 20)
    private String name;

    @Column(name = "gender", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private Gender gender;

    @Column(name = "birth", nullable = false, length = 8)
    private String birth;

    @Column(name = "post", nullable = false, length = 6)
    private String post;

    @Column(name = "add1", nullable = false, length = 100)
    private String add1;

    @Column(name = "add2", nullable = false, length = 100)
    private String add2;

    @Column(name = "phone", nullable = false, length = 11)
    private String phone;

    @Column(name = "point", nullable = false)
    private Integer point;

    @Column(name = "status", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private Status status;

    @Column(name = "org_cd", nullable = false, length = 10)
    @Enumerated(EnumType.STRING)
    private Org_cd org_cd;

}