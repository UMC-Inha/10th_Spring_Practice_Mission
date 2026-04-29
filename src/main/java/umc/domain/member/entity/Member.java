package umc.domain.member.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Getter
@Entity
public class Member {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;
    private String phoneNumber;
    private String name;
    private Integer point;
    private String profileUrl;
}