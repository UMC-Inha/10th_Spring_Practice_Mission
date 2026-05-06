package umc.domain.member.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long member_id;

    private String log_id;
    private String email;
    private String password;
    private String name;
    private String gender;
    private String birth;
    private String post;
    private String add1;
    private String add2;
    private String phone;
    private Integer point;
    private String status;
    private String crt_dt;
    private String org_cd;
    private String chg_dt;
    private String chg_tm;
}